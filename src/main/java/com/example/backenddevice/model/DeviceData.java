package com.example.backenddevice.model;

import java.util.Date;

/**
 * 设备端数据建模
 * @author rsp
 * @version 0.1
 * @date 2021.11.2
 */
public class DeviceData {
    String deviceId;
    Date timestamp;
    String data;

    public DeviceData(String deviceId, Date timestamp, String data) {
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{"
                    + "\"deviceId\":\"" + deviceId + "\","
                    + "\"timestamp\":\"" + timestamp + "\","
                    + "\"data\":\"" + data + "\"" +
                "}";
    }
}
