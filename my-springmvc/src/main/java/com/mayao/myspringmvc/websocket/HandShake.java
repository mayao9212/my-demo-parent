
package com.mayao.myspringmvc.websocket;

import com.talkingdata.aeplus.common.CommonConstant;
import com.talkingdata.aeplus.model.TdUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * Socket建立连接（握手）和断开
 * 
 * @author Goofy
 * @Date 2015年6月11日 下午2:23:09
 */
@Slf4j
public class HandShake implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//		System.out.println("Websocket:用户[ID:" + ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute(CommonConstant.USER) + "]已经建立连接");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//			HttpSession session = servletRequest.getServletRequest().getSession(false);
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// 标记用户
			TdUser tdUser = (TdUser) session.getAttribute(CommonConstant.USER);
			if(tdUser!=null){
				log.warn("WebSocket:用户名：{}，用户id：{},已经建立连接",tdUser.getUserName(),tdUser.getId());
				attributes.put("uid", tdUser.getId());
			}else{
				return false;
			}
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}

}
