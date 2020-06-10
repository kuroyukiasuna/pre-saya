package com.pre.saya;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //marks class as spring MVC controller
public class HomeController {

    @RequestMapping(value="/")
    public String homepage() {
        return "homepage";
    }

    @RequestMapping(value="/artists")
    public String index() { //flags index as the name of template, auto-configured view resolver will map it to /resources/templates/index.html
        return "index";
    }

    @RequestMapping(value="/media")
    public String media() {
        return "media";
    }


}
