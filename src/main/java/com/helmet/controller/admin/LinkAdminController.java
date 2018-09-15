package com.helmet.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helmet.entity.Link;
import com.helmet.service.LinkService;
import com.helmet.util.ResponeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

	@Resource
	private LinkService linkService;

	@RequestMapping("/list")
	public String list(@RequestParam(value = "page") String page, @RequestParam(value = "rows") String pageSize,
			HttpServletResponse response) throws Exception {
		List<Link> links = linkService.getLinkList();
		JSONObject result = new JSONObject();
		JSONArray rows = JSONArray.fromObject(links);
		result.put("rows", rows);
		result.put("total", links.size());
		ResponeUtil.write(result, response);
		return null;
	}

	@RequestMapping("/save")
	public String save(Link link, HttpServletResponse response) throws Exception {
		int num;
		if (link.getLinkId() != null) {
			num = linkService.update(link);
		} else {
			num = linkService.insert(link);
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
		int num = 0;
		for (int i = 0; i < ids.length; i++) {
			num = linkService.delete(ids[i]);
		}
		if (num > 0) {
			result.put("success", true);
		} else {
			result.put("errorInfo", "删除失败，请联系管理员");
		}

		ResponeUtil.write(result, response);
		return null;
	}
}
