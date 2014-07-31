package net.flower.ixmsxms_server.service;

import net.flower.ixmsxms_server.dao.AuthenticationDeviceDao;
import net.flower.ixmsxms_server.dao.UserDao;
import net.flower.ixmsxms_server.domain.AuthenticationDevice;
import net.flower.ixmsxms_server.domain.User;
import net.flower.ixmsxms_server.utils.PaginateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationDeviceService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AuthenticationDeviceDao authenticationDeviceDao;

    public Map<String, Object> insert(AuthenticationDevice authenticationDevice) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.authenticationDeviceDao.insert(authenticationDevice));
        map.put("item", authenticationDevice);
        return map;
    }

    public Map<String, Object> update(AuthenticationDevice authenticationDevice) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.authenticationDeviceDao.update(authenticationDevice));
        return map;
    }

    public Map<String, Object> updateLastLogin(Long userId, String deviceId) {
        Map<String, Object> map = new HashMap<String, Object>();

        AuthenticationDevice authenticationDevice = new AuthenticationDevice();
        authenticationDevice.setUserId(userId);
        authenticationDevice.setDeviceId(deviceId);
        map.put("row", this.authenticationDeviceDao.updateByLastlogin(authenticationDevice));
        return map;
    }

    public Map<String, Object> delete(Long userId, String deviceId) {
        Map<String, Object> map = new HashMap<String, Object>();

        AuthenticationDevice authenticationDevice = new AuthenticationDevice();
        authenticationDevice.setUserId(userId);
        authenticationDevice.setDeviceId(deviceId);
        map.put("row", this.authenticationDeviceDao.delete(authenticationDevice));
        return map;
    }
}