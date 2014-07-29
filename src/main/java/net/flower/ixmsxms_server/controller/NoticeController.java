package net.flower.ixmsxms_server.controller;

import net.flower.ixmsxms_server.domain.Notice;
import net.flower.ixmsxms_server.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/notice")
public class NoticeController extends DefaultController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private NoticeService noticeService;

    @RequestMapping(value="/list", method=RequestMethod.GET)
    @ResponseBody
    public Object list(Notice notice) {
        return this.noticeService.selectList(notice);
    }
}