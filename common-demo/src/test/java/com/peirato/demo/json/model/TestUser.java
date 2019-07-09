package com.peirato.demo.json.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author peirato.
 * @date 2019/7/9
 * @description:
 */
@Data
@AllArgsConstructor
public class TestUser {

    private String name;

    private Integer age;

    private List<String> hobby;


}
