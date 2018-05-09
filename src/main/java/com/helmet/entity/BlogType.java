package com.helmet.entity;

/**
 * 博客类型实体
 * 
 * @author Helmet
 * 2018年5月3日
 */
public class BlogType {
	private Integer typeId;
	private String typeName;//博客类型
	private Integer orderNo;//排序
	private Integer typeCount;//每个类型的博客数量
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getTypeCount() {
		return typeCount;
	}
	public void setTypeCount(Integer typeCount) {
		this.typeCount = typeCount;
	}
	
	
}
