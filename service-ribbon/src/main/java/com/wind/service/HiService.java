package com.wind.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@Service
public class HiService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * ribbon调用配置断路器
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    /**
     * 服务调用异常回调
     * @param name
     * @return
     */
    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
