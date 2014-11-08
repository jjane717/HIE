package org.yijun.hie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zjy on 11/8/14.
 */

@Controller
public class TestController {

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld () {
        return "Hello world";
    }
}
