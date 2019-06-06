package com.tim.ordertest.service.impl;

import com.tim.ordertest.service.CalcProcesseor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class CallTest {

    @Autowired
    public Map<String, CalcProcesseor> processeorMap;

    @PostConstruct
    public void test(){
        processeorMap.keySet().forEach( e -> System.out.println(e));
    }

}
