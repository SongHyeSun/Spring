package com.oracle.oBootMybatis01.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.oracle.oBootMybatis01.handler.SocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	SocketHandler socketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 누군가 URL '/chating' 치면 --> socketHandler 발동
		//socket 세상에서는 얘가  controller 같은 역할을 해준다.
		registry.addHandler(socketHandler, "/chating");

	}

}
