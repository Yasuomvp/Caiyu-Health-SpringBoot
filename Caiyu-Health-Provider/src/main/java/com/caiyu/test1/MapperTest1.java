package com.caiyu.test1;


import com.caiyu.caiyuhealthprovider.CaiyuHealthProviderApplication;
import com.caiyu.dao.JustSetmealMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaiyuHealthProviderApplication.class)
public class MapperTest1 {

    @Autowired
    JustSetmealMapper justSetmealMapper;

    @Test
    public void test() throws Exception {
        System.out.println(justSetmealMapper.findById(12));

    }
}
