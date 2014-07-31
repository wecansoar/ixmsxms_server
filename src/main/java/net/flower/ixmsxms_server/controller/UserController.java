package net.flower.ixmsxms_server.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.flower.ixmsxms_server.domain.AuthenticationDevice;
import net.flower.ixmsxms_server.service.AuthenticationDeviceService;
import net.flower.ixmsxms_server.utils.JSonResultMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.flower.ixmsxms_server.domain.User;
import net.flower.ixmsxms_server.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends DefaultController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @RequestMapping("/authentication")
    @ResponseBody
    public Object authentication(@RequestBody User user){
        return this.userService.authentication(user);
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    @ResponseBody
    public Object view(@PathVariable("userId") Long userId, User user, AuthenticationDevice authenticationDevice) {

        user.setAuthenticationDevice(authenticationDevice);
        System.out.println("VIOLET ");
        System.out.println(user);


        return this.userService.select(user);

    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody User user) {
        return this.userService.insert(user);
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.PUT)
    @ResponseBody
    public Object edit(@PathVariable("userId") Long userId, @RequestBody User user) {
        user.setUserStatus("JOIN");
        return this.userService.update(user);
    }

    @RequestMapping(value="/login", method=RequestMethod.PUT)
    @ResponseBody
    public Object editLogin(User user) {
        return this.userService.updateLastLogin(user);
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("userId") Long userId, User user) {
        return this.userService.delete(user);
    }

}