package com.rishab.shortly.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rishab.shortly.pojo.UrlDto;
import com.rishab.shortly.repo.UrlRepository;
import com.rishab.shortly.util.UrlIdConversionUtil;

@RestController
public class UrlController {

    private static final String BASE_URL = "http://localhost:9091/";
    private final UrlRepository urlRepository;

    @Autowired
    public UrlController(final UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UrlDto getUrl(@PathVariable final String id) {
        return urlRepository.findById(UrlIdConversionUtil.generate(id))
                            .get();
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Map<String, String> shortenUrl(@RequestParam(name = "url", required = true) final String url) {
        UrlDto urlObject = new UrlDto(url);
        urlRepository.save(urlObject);
        Map<String, String> result = new HashMap<>();
        // TODO: extract the base url from the request
        result.put("tiny_url", BASE_URL + UrlIdConversionUtil.getStringRepresentation(urlObject.getId()));
        return result;
    }
}
