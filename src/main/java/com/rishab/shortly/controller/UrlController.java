package com.rishab.shortly.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rishab.shortly.pojo.SaveRequestBody;
import com.rishab.shortly.pojo.ShortlyResponse;
import com.rishab.shortly.pojo.UrlDto;
import com.rishab.shortly.repo.UrlRepository;
import com.rishab.shortly.util.UrlIdConversionUtil;
import com.rishab.shortly.util.UrlUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UrlController {

    private final UrlRepository urlRepository;
    private final int STRING_LENGTH = 6;
    private final String TINY_URL_KEY = "tiny_url";

    @Autowired
    public UrlController(final UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
        log.info("Initialized UrlController");
    }

    @GetMapping(value = "/rest/{id}")
    public ShortlyResponse getUrl(@PathVariable final String id) {
        try {
            return ShortlyResponse.builder()
                                  .data(urlRepository.findById(UrlIdConversionUtil.generate(id))
                                                     .get())
                                  .build();
        } catch (Exception e) {
            log.error("Failed to get URL with exception", e);
            String errorMessage = e.getMessage();
            if (e instanceof NoSuchElementException) {
                errorMessage = "The requested URL does not exist";
            }
            return ShortlyResponse.builder()
                                  .hasError(true)
                                  .errorMessage(errorMessage)
                                  .build();
        }

    }

    @PostMapping(value = "/rest/save")
    public ShortlyResponse shortenUrl(@RequestBody final SaveRequestBody body, HttpServletRequest request) {
        try {
            String url = body.getUrl();
            UrlDto urlObject = new UrlDto(url);
            urlRepository.save(urlObject);
            Map<String, String> result = new HashMap<>();
            String relativePath = UrlIdConversionUtil.getStringRepresentation(urlObject.getId(), STRING_LENGTH);
            result.put(TINY_URL_KEY, UrlUtil.getFullUrl(request, relativePath));
            return ShortlyResponse.builder()
                                  .data(result)
                                  .build();
        } catch (Exception e) {
            return ShortlyResponse.builder()
                                  .hasError(true)
                                  .errorMessage(e.getMessage())
                                  .build();
        }
    }
}
