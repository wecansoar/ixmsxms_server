package net.flower.ixmsxms_server.service;

import net.flower.ixmsxms_server.dao.NoticeDao;
import net.flower.ixmsxms_server.domain.Notice;
import net.flower.ixmsxms_server.utils.PaginateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class NoticeService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private NoticeDao noticeDao;

    public Map<String, Object> selectList(Notice notice) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.noticeDao.selectList(notice));
        int totalCount = this.noticeDao.selectListCount(notice);
        map.put("totalCount", totalCount);
        map.put("page", PaginateTool.paginate(notice.getPage(), 10, notice.getCount(), totalCount));
        return map;
    }

}