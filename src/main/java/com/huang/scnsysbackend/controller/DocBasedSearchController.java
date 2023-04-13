package com.huang.scnsysbackend.controller;

import com.huang.scnsysbackend.pojo.RespBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


@RestController
@RequestMapping("/doc")
public class DocBasedSearchController {

    @PostMapping("/upload")
    public RespBean uploadSingleDocFile(@RequestParam(value = "singleDoc") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (filename.endsWith(".pdf")) {
            Scanner scanner = new Scanner(file.getInputStream());
            System.out.println(scanner.nextLine());
        }
        System.out.println("你好");
        return RespBean.success("ok");
    }

}
