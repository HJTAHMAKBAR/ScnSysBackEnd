package com.huang.scnsysbackend.controller;

import com.huang.scnsysbackend.pojo.RespBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;


@RestController
@RequestMapping("/doc")
public class DocBasedSearchController {

    @PostMapping("/upload")
    public RespBean uploadSingleDocFile(@RequestParam(value = "singleDoc") MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        System.out.printf(file.toString());
        return RespBean.success("ok");
    }

}
