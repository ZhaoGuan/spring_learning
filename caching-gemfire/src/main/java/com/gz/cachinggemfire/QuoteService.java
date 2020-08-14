package com.gz.cachinggemfire;

import lombok.Data;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class QuoteService {
    protected static final String ID_BASED_QUOTE_SERVICE_URL = "http://gturnquist-quoters.cfapps.io/api/{id}";
    protected static final String RANDOM_QUOTE_SERVICE_URL = "http://gturnquist-quoters.cfapps.io/api/random";

    private volatile boolean cacheMiss = false;

    private final RestTemplate quoteServiceTemplate = new RestTemplate();

    public boolean isCacheMiss() {
        boolean cacheMiss = this.cacheMiss;
        this.cacheMiss = false;
        return cacheMiss;
    }

    protected void setCacheMiss() {
        this.cacheMiss = true;
    }

    @Cacheable("Quotes")
    public Quote requestQuote(Long id) {
        setCacheMiss();
        return requestQuote(ID_BASED_QUOTE_SERVICE_URL,
                Collections.singletonMap("id", id));

    }


    @CachePut(cacheNames = "Quotes", key = "#result.id")
    public Quote requestRandomQuote() {
        setCacheMiss();
        return requestQuote(RANDOM_QUOTE_SERVICE_URL);
    }

    protected Quote requestQuote(String URL) {
        return requestQuote(URL, Collections.emptyMap());
    }

    private Quote requestQuote(String URL, Map<String, Object> urlVariables) {
        return Optional.ofNullable(this.quoteServiceTemplate.getForObject(URL,
                QuoteResponse.class, urlVariables))
                .map(QuoteResponse::getQuote)
                .orElse(null);

    }

}
