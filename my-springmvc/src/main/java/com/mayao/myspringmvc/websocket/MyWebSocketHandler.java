package com.mayao.myspringmvc.websocket;

import com.talkingdata.aeplus.common.BaseResponse;
import com.talkingdata.aeplus.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Socket处理器
 * 
 * @author Goofy
 * @Date 2015年6月11日 下午1:19:50
 */
@Slf4j
@Component
public class MyWebSocketHandler implements WebSocketHandler {
	public static final Map<String, WebSocketSession> userSocketSessionMap;

	static {
		userSocketSessionMap = new HashMap<>();
	}

	/**
	 * 建立连接后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {

		String uid = (String) session.getAttributes().get("uid");
		if (userSocketSessionMap.get(uid) == null) {
			log.info("用户id放入map：{}",uid);
			userSocketSessionMap.put(uid, session);
		}
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
			if(message.getPayloadLength()==0){
				return;
			}
//			Message msg=new Gson().fromJson(message.getPayload().toString(),Message.class);
//			msg.setDate(new Date());
//			System.out.println("客户端发送的信息是："+new Gson().toJson(msg));
//			sendMessageToUser(msg.getTo(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
	}

	/**
	 * 消息传输错误处理
	 */
	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap
				.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID；{}" + entry.getKey());
				log.info("Socket会话已经移除:用户ID；{}" + entry.getKey());
				break;
			}
		}
	}

	/**
	 * 关闭连接后
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		System.out.println("Websocket:" + session.getId() + "已经关闭");
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap
				.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				log.info("Socket会话已经移除:用户ID:{}" , entry.getKey());
				break;
			}
		}
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void broadcast(final TextMessage message) throws IOException {
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap
				.entrySet().iterator();

		// 多线程群发
		while (it.hasNext()) {

			final Entry<String, WebSocketSession> entry = it.next();

			if (entry.getValue().isOpen()) {
				// entry.getValue().sendMessage(message);
				new Thread(()-> {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
				}).start();
			}

		}
	}

	/**
	 * 给某个用户发送消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void send(String uid, String message)throws IOException {
//		uid=1L;
		message = new BaseResponse(message).toString();
		TextMessage textMessage = new TextMessage(message);
		log.info("WebSocket发送消息：userId:{},message:{}",uid,message);
		WebSocketSession session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(textMessage);
		}
	}

	public void send(String uid, ResponseEnum responseEnum, String message)throws IOException {
//		uid=1L;
		message = new BaseResponse(responseEnum,message).toString();
		TextMessage textMessage = new TextMessage(message);
		log.info("WebSocket发送消息：userId:{},message:{}",uid,message);
		WebSocketSession session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(textMessage);
		}
	}


}
