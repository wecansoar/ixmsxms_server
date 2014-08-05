package net.flower.ixmsxms_server.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import net.flower.ixmsxms_server.dao.AuthenticationDeviceDao;
import net.flower.ixmsxms_server.domain.AuthenticationDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import net.flower.ixmsxms_server.utils.PaginateTool;
import net.flower.ixmsxms_server.domain.User;
import net.flower.ixmsxms_server.dao.UserDao;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserDao userDao;

    @Resource
    private AuthenticationDeviceDao authenticationDeviceDao;


    public User select(User user) {
        return this.userDao.select(user);
    }

    public Object authentication(User user) {
        // 전화 번호 X + 앱인증키 O
        if (user.getPhone().equals("") && !user.getAuthenticationDevice().getDeviceId().equals("")) {
            return this.authenticationDeviceDao.selectListByDeviceId(user.getAuthenticationDevice().getDeviceId());
        } else if (!user.getPhone().equals("") && !user.getUserId().equals("") && !user.getAuthenticationDevice().getDeviceId().equals("")){
            return this.userDao.selectByUserInfo(user);
        }else{
            throw new IllegalArgumentException("deviceId needed");
        }
    }

    @Transactional
    public Map<String, Object> insert(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row",this.userDao.insert(user));
        Long userId = user.getUserId();

        if( !user.getAuthenticationDevice().getDeviceId().equals("")){
           user.getAuthenticationDevice().setUserId(userId);
           this.authenticationDeviceDao.insert(user.getAuthenticationDevice());
        }
        map.put("item", user);
        return map;
    }

    public Map<String, Object> update(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.userDao.update(user));
        return map;
    }

    @Transactional
    public Map<String, Object> updateLastLogin(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.userDao.updateByLastlogin(user));
        map.put("device", this.authenticationDeviceDao.updateByLastlogin(user.getAuthenticationDevice()));
        return map;
    }

    @Transactional
    public Map<String, Object> delete(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.userDao.delete(user.getUserId()));

        AuthenticationDevice authenticationDevice = new AuthenticationDevice();
        authenticationDevice.setUserId(user.getUserId());
        map.put("device_row",this.authenticationDeviceDao.delete(authenticationDevice));

        return map;
    }
}