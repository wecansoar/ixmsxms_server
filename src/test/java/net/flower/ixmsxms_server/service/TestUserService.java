package net.flower.ixmsxms_server.service;

import net.flower.ixmsxms_server.domain.*;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hyeyoungkang on 2014. 7. 15..
 */
public class TestUserService extends TestCore {


    @Resource
    private UserService userService;

    @Test
    public void insert(){
        User user = new User();
        user.setUserStatus("WAITING");
        AuthenticationDevice authenticationDevice = new AuthenticationDevice();

        authenticationDevice.setDeviceId("IPONE12222220");
        authenticationDevice.setUaInfo("Android3.2");
        user.setAuthenticationDevice(authenticationDevice);

        Map<String, Object> map =  this.userService.insert(user);

        System.out.println(map);
        Assert.assertNotNull(map);
    }

    @Test
    public void select(){
        User user = new User();
        user.setUserId(Long.parseLong("6"));
        User user2 =  this.userService.select(user);

        System.out.println(user2);
        Assert.assertNotNull(user2);
    }
}
