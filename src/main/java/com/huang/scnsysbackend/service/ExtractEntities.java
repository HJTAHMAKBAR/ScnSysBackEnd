package com.huang.scnsysbackend.service;

import com.huang.scnsysbackend.pojo.Type;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExtractEntities {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

    public Map<String, Set<String>> getEntities(String input) {
        Map<String, Set<String>> map = new HashMap();
        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();
        map.put("person", new HashSet<>(collectList(coreLabels, Type.PERSON)));
        map.put("country", new HashSet<>(collectList(coreLabels, Type.COUNTRY)));
        map.put("city", new HashSet<>(collectList(coreLabels, Type.CITY)));
        return map;
    }

    private List<String> collectList(List<CoreLabel> coreLabels, final Type type) {
        return coreLabels
                .stream()
                .filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toList());
    }
}
