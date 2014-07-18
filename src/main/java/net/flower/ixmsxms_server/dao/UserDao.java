package net.flower.ixmsxms_server.dao;

import java.util.List;

import net.flower.ixmsxms_server.domain.User;

@Master
public interface UserDao {
    public User select(Long userId);
    public List<User> selectList(User user);
    public int selectListCount(User user);
    public int insert(User user);
    public int update(User user);
    public int delete(Long userId);
    public int updateByLastlogin(Long userId);
}