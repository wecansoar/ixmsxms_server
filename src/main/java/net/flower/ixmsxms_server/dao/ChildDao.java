package net.flower.ixmsxms_server.dao;

import net.flower.ixmsxms_server.domain.Child;
import net.flower.ixmsxms_server.domain.User;

import java.util.List;

@Master
public interface ChildDao {
    public List<Child> selectListByUserId(Child child);
    public Child select(Long childId);
    public int selectListCountByUserId(Child child);
    public int insert(Child child);
    public int update(Child child);
    public int delete(Long childId);
}
