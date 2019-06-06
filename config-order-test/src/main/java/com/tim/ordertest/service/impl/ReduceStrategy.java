package com.tim.ordertest.service.impl;

import com.tim.ordertest.service.CalcProcesseor;
import org.springframework.stereotype.Component;

@Component("reduce")
public class ReduceStrategy implements CalcProcesseor {

    @Override
    public int calculate(int a, int b) {

        return a - b;
    }
}
