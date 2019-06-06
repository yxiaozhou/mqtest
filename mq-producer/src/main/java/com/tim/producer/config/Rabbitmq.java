package com.tim.producer.config;

import lombok.Data;

@Data
public class Rabbitmq {
    private String host;

    private Integer port;

    private String virtualHost;

    private String username;

    private String password;
}
