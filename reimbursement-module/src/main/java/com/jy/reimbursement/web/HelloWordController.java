package com.jy.reimbursement.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/11/9.
 */
@Controller()
public class HelloWordController {
    @GetMapping("/index")
    public String index(){
        return "layout/layout";
    }
    @GetMapping("/main")
    public String main(){
        return "layout/main";
    }
    @GetMapping("/index2")
    public String index2(){
        return "layout2/layout";
    }
}
