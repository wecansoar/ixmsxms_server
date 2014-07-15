package net.flower.ixmsxms_server.service;

import net.flower.ixmsxms_server.domain.Child;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by hyeyoungkang on 2014. 7. 15..
 */
public class TestChildServcie extends TestCore {


    @Resource
    private ChildService childService;
    @Test
    public void insert(){
        Child child = new Child();
        child.setUserId(Long.parseLong("2"));
        child.setName("나우리");
        child.setImageUrl("");
        child.setSex("F");
        child.setBirthdayDate("20120909");

        System.out.println(child);
        System.out.println( childService);


        Map<String, Object> map =  childService.insert(child);
        System.out.println(map);
        Assert.assertNotNull(map);
    }

    @Test
    public void list(){

        Child child1 =  childService.select(Long.parseLong("1"));
        System.out.println(child1);
        Assert.assertNotNull(child1);
    }
}
