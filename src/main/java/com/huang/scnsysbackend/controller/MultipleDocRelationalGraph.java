package com.huang.scnsysbackend.controller;

import com.huang.scnsysbackend.pojo.FileEntity;
import com.huang.scnsysbackend.pojo.RespBean;
import com.huang.scnsysbackend.pojo.Word;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/mul")
public class MultipleDocRelationalGraph {

    @PostMapping("/word-cloud")
    public RespBean getWordCloud(@RequestBody FileEntity[] mulList) {
        List<String> allEntitiesList = new ArrayList<>();
        for (FileEntity entity : mulList) {
            allEntitiesList.addAll(entity.getList().concatOrganizationAndPerson());
        }
        List<Word> wordList = new ArrayList<>();
        Set<String> stringSet = new HashSet<>(allEntitiesList);
        for (String str : stringSet) {
            wordList.add(new Word(str, Collections.frequency(allEntitiesList, str)));
        }
        return RespBean.success("成功获取词云数据", wordList);
    }

    @PostMapping("/relation-graph")
    public RespBean getRelationGraph() {
        return RespBean.success("成功获取关系图谱数据");
    }
}
