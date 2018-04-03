package com.wind.service.impl;

import com.wind.service.HiService;
import org.springframework.stereotype.Component;

/**
 * 服务断路器处理
 */
@Component
public class HiServiceImpl implements HiService {

    @Override
    public String sayHi(String name) {
        return "sorry " + name;
    }
}
