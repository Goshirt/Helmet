package com.helmet.entity;


public class Link {
	private Integer linkId;
	private String linkName;
	private String linkUrl;
	private Integer orderNum;
	public Integer getLinkId() {
		return linkId;
	}
	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	@Override
	public String toString() {
		return "Link [linkId=" + linkId + ", linkName=" + linkName + ", linkUrl=" + linkUrl + ", orderNum=" + orderNum
				+ "]";
	}
	
	
}
