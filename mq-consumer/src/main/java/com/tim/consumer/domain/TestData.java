package com.tim.consumer.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "test1")
public class TestData {
    private Integer id;

    private String msg;

    @Column(name = "create_time",insertable = false)
    private Date createTime;

    @Column(name = "update_time",insertable = false)
    private Date updateTime;

    @Column(name = "remark")
    private String remark;
}