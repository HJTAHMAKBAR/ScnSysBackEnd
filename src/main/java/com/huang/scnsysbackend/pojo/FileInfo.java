package com.huang.scnsysbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private List<String> city;
    private List<String> country;
    private List<String> location;
    private List<String> organization;
    private List<String> person;

    public List<String> concatOrganizationAndPerson() {
        List<String> res = new ArrayList<>();
        for (String str : organization) {
            res.add(str);
        }
        for (String str : person) {
            res.add(str);
        }
        return res;
    }
}
