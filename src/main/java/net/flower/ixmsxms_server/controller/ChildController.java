package net.flower.ixmsxms_server.controller;

import net.flower.ixmsxms_server.domain.Child;
import net.flower.ixmsxms_server.service.ChildService;
import net.flower.ixmsxms_server.service.UserService;
import net.flower.ixmsxms_server.utils.RequiredReferer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/child")
public class ChildController extends DefaultController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ChildService childService;

    @RequestMapping(value="/list", method=RequestMethod.GET)
    @ResponseBody
    public Object list(Child child) {
        System.out.println("violetjjang ");
        return this.childService.selectListByUserId(child);
    }

    @RequestMapping(value="/{childId}", method=RequestMethod.GET)
    @ResponseBody
    public Object view(@PathVariable("childId") Long childId,Child child){
        return this.childService.select(childId);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody Child child) {
        return this.childService.insert(child);
    }

    @RequestMapping(method=RequestMethod.PUT)
    @ResponseBody
    public Object edit(@RequestBody Child child) {
        return this.childService.update(child);
    }

    @RequestMapping(value="/{childId}", method=RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("childId") Long childId) {
        return this.childService.delete(childId);
    }
}