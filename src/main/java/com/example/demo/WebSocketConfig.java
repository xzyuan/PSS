package com.example.demo;

import com.example.demo.controller.PersonnelCountController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(personnelCountHandler(), "personnelCount").setAllowedOrigins("*");
    }
    public WebSocketHandler personnelCountHandler() {
        return new PersonnelCountController();
    }
}
