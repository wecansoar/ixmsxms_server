package net.flower.ixmsxms_server.controller;

import net.flower.ixmsxms_server.domain.UploadImageFile;
import net.flower.ixmsxms_server.domain.User;
import net.flower.ixmsxms_server.service.UploadService;
import net.flower.ixmsxms_server.utils.JSonResultMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Resource
    private UploadService uploadService;

    @RequestMapping("/index")
	public String index() {
        return "index";
	}

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    @SuppressWarnings("rawtypes")
    @JSonResultMapping
    public Object upload( HttpServletRequest request, UploadImageFile uploadImageFile, User user) throws Exception {
        return this.uploadService.upload(request, uploadImageFile, user);
    }

    @RequestMapping("/error/{code}")
    public String error(HttpServletRequest request, @PathVariable("code") String code) {
        System.out.println("error----------" + code);
        request.setAttribute("code", code);
        return "error";
    }
}