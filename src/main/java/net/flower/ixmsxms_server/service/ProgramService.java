package net.flower.ixmsxms_server.service;

import net.flower.ixmsxms_server.dao.NoticeDao;
import net.flower.ixmsxms_server.dao.ProgramDao;
import net.flower.ixmsxms_server.domain.Notice;
import net.flower.ixmsxms_server.domain.Program;
import net.flower.ixmsxms_server.utils.PaginateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProgramService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ProgramDao programDao;

    public Program select() {
        return this.programDao.select();
    }

}