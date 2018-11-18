package com.rishab.shortly.pojo;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ShortlyResponse {

    private Object data;
    private boolean hasError;
    private String errorMessage;
}
