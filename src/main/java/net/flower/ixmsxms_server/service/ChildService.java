package net.flower.ixmsxms_server.service;

import net.flower.ixmsxms_server.dao.ChildDao;
import net.flower.ixmsxms_server.domain.Child;
import net.flower.ixmsxms_server.utils.PaginateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChildService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ChildDao childDao;

    public Child select(Long childId){
        return this.childDao.select(childId);
    }

    public Map<String, Object> selectListByUserId(Child child) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.childDao.selectListByUserId(child));
        int totalCount = this.childDao.selectListCountByUserId(child);
        map.put("totalCount", totalCount);
        map.put("page", PaginateTool.paginate(child.getPage(), 10, child.getCount(), totalCount));
        return map;
    }
    public Map<String, Object> insert(Child child) {
        Map<String, Object> map = new HashMap<String, Object>();

        if( child.getUserId().equals("") || child.getName().equals("")){
            throw new  IllegalArgumentException("parameter exception ");
        }

        System.out.println(child );
        map.put("row", this.childDao.insert(child));
        map.put("item", child);
        return map;
    }

    public Map<String, Object> update(Child child) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.childDao.update(child));
        return map;
    }

    public Map<String, Object> delete(Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.childDao.delete(userId));
        return map;
    }
}