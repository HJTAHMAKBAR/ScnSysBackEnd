package com.huang.scnsysbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private String[] city;
    private String[] country;
    private String[] location;
    private String[] organization;
    private String[] person;
}
