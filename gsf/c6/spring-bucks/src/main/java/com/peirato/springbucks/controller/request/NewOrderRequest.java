package com.peirato.springbucks.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Yang zeqi
 * @date 2019/11/25
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderRequest {

    private String customer;

    private List<String> item;
}
