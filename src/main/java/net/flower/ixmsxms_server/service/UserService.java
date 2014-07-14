package net.flower.ixmsxms_server.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import net.flower.ixmsxms_server.utils.PaginateTool;
import net.flower.ixmsxms_server.domain.User;
import net.flower.ixmsxms_server.dao.UserDao;

@Service
public class UserService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserDao userDao;

    public User select(Long userId) {
        return this.userDao.select(userId);
    }

    public List<User> selectAllList(User user) {
        return this.userDao.selectAllList(user);
    }

    public Map<String, Object> selectList(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.userDao.selectList(user));
        int totalCount = this.userDao.selectListCount(user);
        map.put("totalCount", totalCount);
        map.put("page", PaginateTool.paginate(user.getPage(), 10, user.getCount(), totalCount));
        return map;
    }

    public Map<String, Object> insert(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.userDao.insert(user));
        map.put("item", user);
        return map;
    }

    public Map<String, Object> update(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.userDao.update(user));
        return map;
    }

    public Map<String, Object> delete(Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.userDao.delete(userId));
        return map;
    }
}