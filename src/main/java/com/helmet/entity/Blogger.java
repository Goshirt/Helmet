package com.helmet.entity;

/**
 * 博主
 * 
 * @author Helmet
 * 2018年5月2日
 */
public class Blogger {
	private Integer bloggerId;
	private String bloggerName;//登录名
	private String password;//密码
	private String intro;//个人介绍
	private String nickName;//昵称
	private String sign;//个人签名
	private String imageName;//头像图片名
	
	public Integer getBloggerId() {
		return bloggerId;
	}
	public void setBloggerId(Integer bloggerId) {
		this.bloggerId = bloggerId;
	}
	public String getBloggerName() {
		return bloggerName;
	}
	public void setBloggerName(String bloggerName) {
		this.bloggerName = bloggerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Override
	public String toString() {
		return "Blogger [bloggerId=" + bloggerId + ", bloggerName=" + bloggerName + ", password=" + password
				+ ", intro=" + intro + ", nickName=" + nickName + ", sign=" + sign + ", imageName=" + imageName + "]";
	}
	
}
