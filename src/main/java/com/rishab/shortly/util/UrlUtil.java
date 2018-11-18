package com.rishab.shortly.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlUtil {

    private static final String PROTOCOL_SEPARATOR = "://";

    public String getFullUrl(final HttpServletRequest request, final String relativePath) throws MalformedURLException, URISyntaxException {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(getBaseUrl(request))
                                                                        .path(relativePath);
        return uriComponentsBuilder.toUriString();
    }

    public String getBaseUrl(final HttpServletRequest request) throws MalformedURLException {
        URL url = new URL(request.getRequestURL()
                                 .toString());
        return url.getProtocol() + PROTOCOL_SEPARATOR + url.getAuthority();

    }

    public boolean isValid(final String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
