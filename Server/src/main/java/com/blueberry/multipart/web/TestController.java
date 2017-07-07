package com.blueberry.multipart.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 7/6/2017.
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("name", "blueberry");
        return "multipart";
    }
}
