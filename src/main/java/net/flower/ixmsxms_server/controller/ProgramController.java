package net.flower.ixmsxms_server.controller;

import net.flower.ixmsxms_server.domain.Notice;
import net.flower.ixmsxms_server.service.NoticeService;
import net.flower.ixmsxms_server.service.ProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/program")
public class ProgramController extends DefaultController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ProgramService programService;

    @RequestMapping(value="", method=RequestMethod.GET)
    @ResponseBody
    public Object list() {
        return this.programService.select();
    }
}