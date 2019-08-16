package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.Gooo;
import com.example.demo.util.CAUtil;
import com.example.demo.vo.Response;
import com.example.demo.vo.ResponseBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("demo")
public class TestController {

    @GetMapping("testCAutil")
    public void testCAutil(){
        CAUtil.test();
    }

    @GetMapping("testVo")
    public Response testResvo(){
      /*  RestTemplate restTemplate = new RestTemplate();
        try {

            RequestEntity.get(new URI("")).
                    header("123","456").
                    accept(MediaType.APPLICATION_JSON);
            JSONObject jsonObject = new JSONObject();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
//        Response response = new Response();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("emrPhcItemList", Arrays.asList("1", "2", "3"));
//        Map<String,Object> map = new HashMap<>();
//        map.put("emrPhcItemList", Arrays.asList("1", "2", "3"));
//        response.setObject(map);
//        Response response = ResponseBuilder.success().add(new Gooo()).add(new Gooo()).setMessage("haha").build();
        Response response = ResponseBuilder.failure().put("emrPhcItemList",new Gooo()).setMessage("heh").build();
        return response;
    }
}
