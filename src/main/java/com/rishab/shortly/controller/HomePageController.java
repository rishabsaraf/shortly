package com.rishab.shortly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomePageController {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }
}
