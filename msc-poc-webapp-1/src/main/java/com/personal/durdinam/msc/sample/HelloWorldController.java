package com.personal.durdinam.msc.sample;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by durdinam on 2017-03-01.
 */
@Controller
public class HelloWorldController {

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        HelloWorld hw = new HelloWorld();

        model.put("title", "Spring MVC lightweight sample");
        model.put("hw", hw);

        return "index";
    }

}
