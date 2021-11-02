package com.example.backenddevice.server;

import com.example.backenddevice.model.DeviceData;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * WebSocket协议的设备模拟器
 * @author rsp
 * @version 0.1
 * @date 2021.11.2
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{deviceName}")
public class WebSocketDevice {

//    public static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    private Session session;

    private String deviceName;

    private boolean running = false;

    @OnOpen
    public void onOpen(Session session, @PathParam("deviceName") String deviceName) {
        log.info("和边缘端成功接入, 本设备名称：{}", deviceName);
        this.session = session;
        this.deviceName = deviceName;
        this.running = true;
//        ActiveSendDeviceData();
    }

    @OnClose
    public void onClose(Session session) {
//        sessionMap.remove(session.getId());
        log.info("边缘端停止连接，session_id: {}", session.getId());
        this.session = null;
        this.running = false;
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("接收到客户端(session_id: {})的消息：{}", session.getId(), message);
        PassiveSendDeviceData();
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("来自客户端(session_id: {})的连接发生错误，错误信息为：{}", session.getId(), error.getMessage());
        this.session = null;
        this.running = false;
    }

    private void ActiveSendDeviceData() {
        try {
            while (this.running) {
                String data = String.valueOf(UUID.randomUUID());
                DeviceData deviceData = new DeviceData(this.deviceName, new Date(), data);
                sendMessage(deviceData.toString());
                sleep(1000);
            }
        } catch (InterruptedException | IOException e) {
            log.info(e.getMessage());
        }
    }

    private void PassiveSendDeviceData() {
        try {
            if (this.running) {
                String data = String.valueOf(UUID.randomUUID());
                DeviceData deviceData = new DeviceData(this.deviceName, new Date(), data);
                sendMessage(deviceData.toString());
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    /**
     * 设备端推送消息给边缘端
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
