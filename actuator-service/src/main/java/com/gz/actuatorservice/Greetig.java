package com.gz.actuatorservice;

import lombok.Data;

@Data
public class Greetig {
    private final long id;
    private final String content;

    public Greetig(long id, String content) {
        this.id = id;
        this.content = content;
    }

}
