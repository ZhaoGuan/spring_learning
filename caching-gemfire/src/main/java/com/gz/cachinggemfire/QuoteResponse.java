package com.gz.cachinggemfire;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteResponse {
    @JsonProperty("value")
    private Quote quote;

    @JsonProperty("type")
    private String status;

    @Override
    public String toString() {
        return String.format("{ @type = %1%s , quote '%2%s' , status = %3$s }",
                getClass().getName(), getQuote(), getStatus());
    }
}
