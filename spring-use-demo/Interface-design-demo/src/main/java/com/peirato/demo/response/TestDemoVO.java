package com.peirato.demo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestDemoVO {

    private String name;

    private Integer status;
}
