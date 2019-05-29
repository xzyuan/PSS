package com.example.demo;

import com.example.demo.dao.EventDao;
import com.example.demo.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {



    @Autowired
    private EventDao eventDao;

    @Test
    public void contextLoads() {

    }

}
