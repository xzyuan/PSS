package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.epics.ca.Channel;
import org.epics.ca.Context;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonnelCountController extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        Map<String, String> payload = new HashMap<>();
        List<String> pvList = new ArrayList<>();
        pvList.add("HLS:PSS:Linac:PersonnelCount:longin");
        pvList.add("HLS:PSS:Ringcenter:PersonnelCount:longin");
        pvList.add("HLS:PSS:Ringhall:PersonnelCount:longin");
        try (Context context = new Context()) {
            List<Channel<Double>> channelList = new ArrayList<Channel<Double>>();
            for (int i = 0; i < pvList.size(); i++) {
                channelList.add(context.createChannel(pvList.get(i), Double.class));
                channelList.get(i).connect();
            }
            int time = 1000;
            while(true){
                try {
                    Thread.sleep(time);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                for (int i = 0; i < channelList.size(); i++) {
                    payload.put(pvList.get(i),channelList.get(i).get().toString());
                }
                session.sendMessage(new TextMessage(JSONObject.toJSONString(payload)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
