package com.helmet.realm;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.helmet.entity.Blogger;
import com.helmet.service.BloggerService;

public class MyRealm extends AuthorizingRealm{
	
	
	@Resource
	private BloggerService bloggerService;
	
	/**
	 * 为当前的登录用户授予用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 验证当前的登录用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取前台登录表单的的用户名,在封装token时会把登录的用户名放到token的userName中
		String bloggerName=(String) token.getPrincipal();
		//获取数据库中的与bloggerName匹配的blogger信息
		Blogger blogger=bloggerService.getBloggerByName(bloggerName);
		if (blogger!=null) {
			//把信息封装到session中
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(blogger.getBloggerName(), blogger.getPassword(), "xxx");
			return authcInfo;
		}else{
			return null;
		}
	}

}
