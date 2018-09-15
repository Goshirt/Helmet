package com.helmet.service;

import java.util.List;

import com.helmet.entity.Link;

public interface LinkService {
	/**
	 * 获取项目链接列表
	 * @return
	 */
	public List<Link> getLinkList();
	
	/**
	 * 新增项目链接
	 * @param link
	 * @return
	 */
	public Integer insert(Link link);
	
	/**
	 * 更新项目链接
	 * @param link
	 * @return
	 */
	public Integer update(Link link);
	
	/**
	 * 删除项目链接
	 * @param linkId
	 * @return
	 */
	public Integer delete(String linkId);
}
