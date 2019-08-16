package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Response {

    private boolean success;// 是否成功

    private String msg;// 提示信息

    private Object object;// 其他信息


}
