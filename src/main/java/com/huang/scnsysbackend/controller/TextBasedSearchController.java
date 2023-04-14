package com.huang.scnsysbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.huang.scnsysbackend.pojo.RespBean;
import com.huang.scnsysbackend.service.ExtractEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/text")
public class TextBasedSearchController {

    @Autowired
    private ExtractEntities extractEntities;

    @PostMapping("/extract-entities")
    public RespBean ExtractEntities(@RequestBody JSONObject body) {
        String inputText = body.getString("inputText");
        return RespBean.success("提取命名实体完成!", extractEntities.getEntities(inputText));
    }

}
