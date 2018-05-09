package com.helmet.lucene;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.helmet.entity.Blog;
import com.helmet.util.DateUtil;
import com.helmet.util.StringUtil;


/**
 * Lucene索引工具类
 * 
 * @author Helmet
 * 2018年5月7日
 */
public class BlogIndex {
	
	//对博客内容进行索引后索引保存的路径
	private Directory directory;
	
	/**
	 * 获得索引写入类
	 * @return
	 * @throws Exception
	 */
	private IndexWriter getIndexWriter()throws Exception {
		//初始化路径
		directory=FSDirectory.open(Paths.get("H:\\VIP\\lucene"));
		//中文分析器
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
		//配置类
		IndexWriterConfig config=new IndexWriterConfig(analyzer);
		//实例化indexWriter
		IndexWriter indexWriter=new IndexWriter(directory, config);
		return indexWriter;
	}
	
	/**
	 * Blog变成索引文件，并输出到指定目录
	 * @param blog
	 * @throws Exception
	 */
	public void blogToIndex(Blog blog)throws Exception {
		IndexWriter indexWriter=getIndexWriter();
		Document document=new Document();
		//StringField定义的字段不会被分词器解析
		document.add(new StringField("blogId", String.valueOf(blog.getBlogId()), Field.Store.YES));
		document.add(new StringField("releaseDate", DateUtil.formateDateToString(new Date(), "yyyy-MM-dd"), Field.Store.YES));
		document.add(new org.apache.lucene.document.TextField("title", blog.getTitle(), Field.Store.YES));
		document.add(new org.apache.lucene.document.TextField("content", blog.getContentNoTag(), Field.Store.YES));
		//输出索引文件到indexWriter指定的目录下
		indexWriter.addDocument(document);
		indexWriter.close();
	}
	
		public List<Blog> serachBlog(String serachField)throws Exception{
			directory=FSDirectory.open(Paths.get("H:\\VIP\\lucene"));
			//从保存索引的目录获得一个索引读取器
			IndexReader indexReader=DirectoryReader.open(directory);
			//获取一个可以进行多查询的查询器，如本项目的需求，title and content
			BooleanQuery.Builder qBuilder=new BooleanQuery.Builder();
			SmartChineseAnalyzer chineseAnalyzer=new SmartChineseAnalyzer();
			
			//在document中定义的title中进行查询
			QueryParser queryParser=new QueryParser("title", chineseAnalyzer);
			Query query=queryParser.parse(serachField);
			
			//在document中定义的content中进行查询
			QueryParser queryParser2=new QueryParser("content", chineseAnalyzer);
			Query query2=queryParser2.parse(serachField);
			
			//SHOULD表示的或，查询时，title和content中查询是或的关系，相当于多条件查询
			qBuilder.add(query, BooleanClause.Occur.SHOULD);
			qBuilder.add(query2, BooleanClause.Occur.SHOULD);
			
			//查询
			IndexSearcher indexSearcher=new IndexSearcher(indexReader);
			//从指定的查询器中获取最多一百条记录，会自动评分
			TopDocs topDocs=indexSearcher.search(qBuilder.build(), 100);
			//优先从title的query中查询
			QueryScorer scorer=new QueryScorer(query);
			Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
			//把查询到的匹配的字段加上html标签，粗体加红色显示
			SimpleHTMLFormatter sHtmlFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
			//按设置好的格式就行高亮显示
			Highlighter highlighter=new Highlighter(sHtmlFormatter, scorer);
			highlighter.setTextFragmenter(fragmenter);
			
			List<Blog> blogs=new LinkedList<Blog>();
			//遍历得分最多的一百条记录
			for (ScoreDoc scoreDoc: topDocs.scoreDocs) {
				Document document=indexSearcher.doc(scoreDoc.doc);
				Blog blog=new Blog();
				//设置查询后blog显示的内容
				blog.setBlogId(Integer.parseInt(document.get("blogId")));
				blog.setReleaseDateStr(document.get("releaseDate"));
				//因为title和content是用来被查询的，需要进一步处理,同时使用comment共公包对内容就行标签过滤
				String title=StringEscapeUtils.escapeHtml(document.get("title"));
				String content=StringEscapeUtils.escapeHtml(document.get("content"));
				
				//处理title
				if (title!=null) {
					//title不为空时，把title进行解析
					TokenStream tokenStream=chineseAnalyzer.tokenStream("title", new StringReader(title));
					//获得高亮的title
					String highlighterTitle=highlighter.getBestFragment(tokenStream, title);
					if (StringUtil.isNotEmpty(highlighterTitle)) {
						blog.setTitle(highlighterTitle);
					}else {
						blog.setTitle(title);
					}
				}
				
				//处理content
				if (content!=null) {
					//title不为空时，把title进行解析
					TokenStream tokenStream=chineseAnalyzer.tokenStream("content", new StringReader(content));
					//获得高亮的title
					String highlighterContent=highlighter.getBestFragment(tokenStream, content);
					if (StringUtil.isNotEmpty(highlighterContent)) {
						//如果content过长，进行截取，便于页面展示
						if (highlighterContent.length()<=200) {
							blog.setContent(highlighterContent);
						}else {
							blog.setContent(highlighterContent.substring(0, 200));
						}
					}else {
						blog.setContent(content);
					}
				}
				//添加blog到集合
				blogs.add(blog);
				
			}
			return blogs;
		}
		
	
}
