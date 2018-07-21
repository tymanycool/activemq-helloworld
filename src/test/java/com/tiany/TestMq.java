package com.tiany;


import com.tiany.activemq.MyMessageListener;
import com.tiany.activemq.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMq {

    @Autowired
    TestService testService;

    @Autowired
    MyMessageListener myMessageListener;
    @Test
    public void test() throws Exception {
        testService.test();
        System.out.println(testService);
        System.in.read();
    }

    @Test
    public void test2() throws Exception {
        System.out.println(myMessageListener);
        System.in.read();
    }
}
