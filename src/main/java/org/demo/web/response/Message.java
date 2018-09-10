package org.demo.web.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public final class Message {
    private String message;
}

