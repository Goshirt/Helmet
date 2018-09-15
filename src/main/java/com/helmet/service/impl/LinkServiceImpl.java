package com.helmet.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.helmet.dao.LinkDao;
import com.helmet.entity.Link;
import com.helmet.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService{
	
	@Resource
	private LinkDao linkDao;
	
	public List<Link> getLinkList() {
		return linkDao.getLinkList();
	}

	public Integer insert(Link link) {
		return linkDao.insert(link);
	}

	public Integer update(Link link) {
		return linkDao.update(link);
	}

	public Integer delete(String linkId) {
		return linkDao.delete(linkId);
	}

}
