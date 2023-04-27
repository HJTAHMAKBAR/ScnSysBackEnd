package com.huang.scnsysbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Node {
    private String id;
    private String text;
    private String color;
    private String borderColor;

    public Node(String id, String text, String color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }

    public Node(String id, String text, String color, String borderColor) {
        this.id = id;
        this.text = text;
        this.color = color;
        this.borderColor = borderColor;
    }
}
