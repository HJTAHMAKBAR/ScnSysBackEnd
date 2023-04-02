package com.huang.scnsysbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.huang.scnsysbackend.pojo.RespBean;
import com.huang.scnsysbackend.pojo.Type;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/text")
public class TextBasedSearchController {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

    @PostMapping("/extract-entities")
    public RespBean ExtractEntities(@RequestBody JSONObject body) {
        String inputText = body.getString("inputText");
        CoreDocument coreDocument = new CoreDocument(inputText);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();
        Map<String, Set<String>> map = new HashMap();
        map.put("person", new HashSet<>(collectList(coreLabels, Type.PERSON)));
        map.put("country", new HashSet<>(collectList(coreLabels, Type.COUNTRY)));
        map.put("city", new HashSet<>(collectList(coreLabels, Type.CITY)));
        return RespBean.success("提取命名实体完成!", map);
    }

    private List<String> collectList(List<CoreLabel> coreLabels, final Type type) {
        return coreLabels
                .stream()
                .filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toList());
    }

}
