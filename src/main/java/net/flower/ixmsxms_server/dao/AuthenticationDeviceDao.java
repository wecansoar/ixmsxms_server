package net.flower.ixmsxms_server.dao;

import net.flower.ixmsxms_server.domain.AuthenticationDevice;
import net.flower.ixmsxms_server.domain.User;

import java.util.List;

@Master
public interface AuthenticationDeviceDao {
    public AuthenticationDevice selectListByDeviceId(String deviceId);

    public int selectListCount(AuthenticationDevice authenticationDevice);
    public int insert(AuthenticationDevice authenticationDevice);
    public int update(AuthenticationDevice authenticationDevice);
    public int delete(AuthenticationDevice authenticationDevice);
    public int updateByLastlogin(AuthenticationDevice authenticationDevice);
}