package net.flower.ixmsxms_server.controller;


import net.flower.ixmsxms_server.domain.Growth;
import net.flower.ixmsxms_server.domain.GrowthChildMap;
import net.flower.ixmsxms_server.domain.GrowthItem;
import net.flower.ixmsxms_server.service.GrowthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/growth")
public class GrowthController extends DefaultController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private GrowthService growthService;



    @RequestMapping(value="/{growthId}", method=RequestMethod.GET)
    @ResponseBody
    public Object view(@PathVariable("growthId") Long growthId ,  Growth growth) {
//        growth.setGrowthId(growthId);
        return this.growthService.select(growthId);
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    @ResponseBody
    public Object list(Growth growth) {
        return this.growthService.selectListByUserId(growth);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody Growth growth, @RequestBody List<GrowthChildMap> growthChildMaps, @RequestBody List<GrowthItem> growthItems) {

        return this.growthService.insert(growth, growthChildMaps, growthItems);
    }
//    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
//    @ResponseBody
//    public Object view(@PathVariable("userId") Long userId, User user) {
//        return this.userService.select(userId);
//    }
//
//    @RequestMapping(method=RequestMethod.POST)
//    @ResponseBody
//    public Object add(@RequestBody User user) {
//        return this.userService.insert(user);
//    }
//
//    @RequestMapping(value="/{userId}", method=RequestMethod.PUT)
//    @ResponseBody
//    public Object edit(@PathVariable("userId") Long userId, @RequestBody User user) {
//        return this.userService.update(user);
//    }
//
//    @RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
//    @ResponseBody
//    public Object delete(@PathVariable("userId") Long userId) {
//        return this.userService.delete(userId);
//    }

}