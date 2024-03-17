package com.flx.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataType {

    private int id;

    private long parent_id;

    private String name;

    private List<String> strList;

    private Map<String, String> map;

    private Map<String, MapValueObject> mapObject;

    private Course course;

    private List<Course> courseList;

}
