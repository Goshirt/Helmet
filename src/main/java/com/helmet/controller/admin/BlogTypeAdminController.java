package com.helmet.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helmet.entity.BlogType;
import com.helmet.entity.PageBean;
import com.helmet.service.BlogService;
import com.helmet.service.BlogTypeService;
import com.helmet.util.ResponeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private BlogService blogService;

	@RequestMapping("/list")
	public String list(@RequestParam(value = "page") String page, @RequestParam(value = "rows") String pageSize,
			HttpServletResponse response) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(pageSize));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<BlogType> blogTypes = blogTypeService.list(map);
		Long count = blogTypeService.countType();
		JSONObject result = new JSONObject();
		JSONArray rows = JSONArray.fromObject(blogTypes);
		result.put("rows", rows);
		result.put("total", count);
		ResponeUtil.write(result, response);
		return null;
	}

	@RequestMapping("/save")
	public String save(BlogType blogType, HttpServletResponse response) throws Exception {
		int num;
		if (blogType.getTypeId() != null) {
			num = blogTypeService.update(blogType);
		} else {
			num = blogTypeService.add(blogType);
		}
		JSONObject result = new JSONObject();
		if (num > 0) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		ResponeUtil.write(result, response);
		return null;
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "deleteIds") String deleteIds, HttpServletResponse response)
			throws Exception {
		String[] ids = deleteIds.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < ids.length; i++) {
			int flag = blogService.haveBlog(Integer.parseInt(ids[i]));
			if (flag > 0) {
				result.put("errorInfo", "该博客类别下有博客，无法删除");
			} else {
				int num = blogTypeService.delete(Integer.parseInt(ids[i]));
				if (num > 0) {
					result.put("success", true);
				} else {
					result.put("errorInfo", "删除失败，请联系管理员");
				}
			}
		}
		ResponeUtil.write(result, response);
		return null;
	}
}
