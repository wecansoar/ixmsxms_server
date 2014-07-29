package net.flower.ixmsxms_server.dao;

import net.flower.ixmsxms_server.domain.Notice;
import net.flower.ixmsxms_server.domain.User;

import java.util.List;

@Master
public interface NoticeDao {
    public List<Notice> selectList(Notice notice);
    public int selectListCount(Notice notice);
}