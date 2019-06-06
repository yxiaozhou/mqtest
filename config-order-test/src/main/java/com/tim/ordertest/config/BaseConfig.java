package com.tim.ordertest.config;

import lombok.Data;

@Data
public class BaseConfig {
    public int id;

    public BaseConfig(int id) {
        this.id = id;
    }

    public BaseConfig() {
    }
}
