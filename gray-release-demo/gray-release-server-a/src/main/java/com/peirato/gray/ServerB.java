package com.peirato.gray;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-b")
public interface ServerB {

    @GetMapping("/get")
    String get();
}
