package com.huang.scnsysbackend.controller;

import com.huang.scnsysbackend.pojo.FileEntity;
import com.huang.scnsysbackend.pojo.FileInfo;
import com.huang.scnsysbackend.pojo.RespBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mul")
public class MultipleDocRelationalGraph {

    @PostMapping("/word-cloud")
    public RespBean getWordCloud(@RequestBody FileEntity[] mulList) {
        for (FileEntity entity : mulList) {
            System.out.println(entity.getName());
            System.out.println(entity.getList().toString());
        }
        return RespBean.success("成功");
    }
}
