package com.mango.web.controller;

import com.mango.web.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by tobywang on 4/10/2018.
 */
@Controller
public class IndexController {
    private static Logger logger = Logger.getLogger(IndexController.class);
    @Resource
    private UserService userService;
    
    @RequestMapping("/index")
    public String index(ModelMap model) {
        int count = userService.getAll().size();
        model.put("count", count);
        logger.info("Get User Count " + count);
        return "index";
    }
    
    /*@RequestMapping("/index")
    public String index(@RequestParam("site") String site, ModelMap model) {
        model.put("count", userService.getAll().size());
        return "index";
    }*/
}
