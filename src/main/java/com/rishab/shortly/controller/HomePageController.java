package com.rishab.shortly.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rishab.shortly.pojo.UrlDto;
import com.rishab.shortly.repo.UrlRepository;
import com.rishab.shortly.util.UrlIdConversionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomePageController {

    private final UrlRepository urlRepository;

    @Autowired
    public HomePageController(final UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String homePage(@PathVariable final Optional<String> id, final HttpServletResponse response) {
        if (id.isPresent()) {
            try {
                UrlDto urlDto = urlRepository.findById(UrlIdConversionUtil.generate(id.get()))
                                             .get();
                response.sendRedirect(urlDto.getUrl());
            } catch (Exception e) {
                log.error("Failed", e);
                return "pageNotFound";
            }
        }
        return "index";
    }
}
