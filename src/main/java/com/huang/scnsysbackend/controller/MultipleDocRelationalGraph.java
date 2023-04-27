package com.huang.scnsysbackend.controller;

import com.huang.scnsysbackend.pojo.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
    public RespBean getRelationGraph(@RequestBody FileEntity[] mulList) {
        String[] colors ={"red", "green", "blue", "pink", "purple", "grey"};
        List<Node> nodes = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        int colorIndex = 0;
        for (FileEntity entity : mulList) {
            String curColor = colors[colorIndex++];
            Node root = new Node(entity.getName(), entity.getName(), curColor, "black");
            nodes.add(root);
            List<String> list = entity.getList().concatOrganizationAndPerson();
            list.forEach(name -> {
                String cId = root.getId() + '_' + name;
                nodes.add(new Node(cId, name, curColor));
                lines.add(new Line(root.getId(), cId));
            });
        }
        Map<String, List<String>> map = nodes.stream().collect(Collectors.groupingBy(Node::getText, Collectors.mapping(Node::getId, Collectors.toList())));
        map.values().forEach(associationNodes -> {
            for (int i = 0; i < associationNodes.size() - 1; i++) {
                lines.add(new Line(associationNodes.get(i), associationNodes.get(i + 1)));
            }
        });
        Map<String, Object> json = new HashMap<>();
        json.put("nodes", nodes);
        json.put("lines", lines);
        return RespBean.success("成功获取关系图谱数据", json);
    }
}
