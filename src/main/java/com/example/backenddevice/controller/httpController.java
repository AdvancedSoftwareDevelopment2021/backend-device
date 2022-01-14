package com.example.backenddevice.controller;

import com.example.backenddevice.entity.Response;
import com.example.backenddevice.model.DeviceData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * @author dyanjun
 * @date 2021/11/20 0:14
 */
@RestController
@RequestMapping(value = "/device")
public class httpController {

    @GetMapping(value = "/data")
    public ResponseEntity<?> sendData() {
        DeviceData deviceData = new DeviceData("deviceId", new Date(),  String.valueOf(UUID.randomUUID()));
        return new ResponseEntity<>(new Response(200, "获取成功", deviceData), HttpStatus.OK);
    }

    @PostMapping(value = "/command")
    public ResponseEntity<?> sendCommand(@RequestBody String obj) {
        System.out.println(obj);
        return new ResponseEntity<>(new Response(200, "命令发送成功", obj), HttpStatus.OK);
    }

    @PostMapping(value = "/pic")
    public ResponseEntity<?> sendPic() {
        System.out.println("图片hhh");
        File file = new File("C:\\Users\\29472\\Desktop\\微信图片_20211101201711.jpg"); //相对路径使用不了的话,使用图片绝对路径
        if(!file.exists()){//判断文件是否存在
            System.out.print("文件不存在");
            return new ResponseEntity<>(new Response(500, "返回图片失败", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response(200, "获取图片成功", file), HttpStatus.OK);
    }
}
