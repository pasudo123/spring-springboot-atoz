package edu.pasudo123.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("v3")
public class FruitController {

    @ResponseBody
    @RequestMapping(value = "fruit/{name}", method = RequestMethod.GET)
    public String getFruitName(@PathVariable("name") Type type){
        return type.name();
    }

    enum Type{
        APPLE, BANANA, GRAPE
    }
}
