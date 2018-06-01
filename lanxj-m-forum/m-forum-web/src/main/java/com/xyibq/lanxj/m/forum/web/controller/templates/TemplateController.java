package com.xyibq.lanxj.m.forum.web.controller.templates;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

    @RequestMapping("/postInfo")
    public  String test(){
        return "controlTest/PostInfoTest";
    }


    @RequestMapping("/test1")
    public  String test1(){
        return "Test1Controller";
    }

    @RequestMapping("/test2")
    public  String test2(){
        return "Test2Controller";
    }


}
