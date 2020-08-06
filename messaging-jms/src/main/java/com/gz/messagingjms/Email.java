package com.gz.messagingjms;

import lombok.Data;

@Data
public class Email {
    private String to;
    private String body;

    public Email(String to, String body) {
        this.to = to;
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("Email{to=%s, body=%s}", getTo(), getBody());
    }
}
