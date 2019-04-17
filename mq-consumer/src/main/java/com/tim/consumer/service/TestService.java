package com.tim.consumer.service;

import com.tim.consumer.domain.TestData;
import com.tim.consumer.mapper.TestDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private TestDataMapper testDataMapper;

    public void saveData(TestData t){
        testDataMapper.insert(t);
    }

}
