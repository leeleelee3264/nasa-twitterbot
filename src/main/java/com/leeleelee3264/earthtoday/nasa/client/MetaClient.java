package com.leeleelee3264.earthtoday.nasa.client;

import com.leeleelee3264.earthtoday.nasa.dto.Meta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
public class MetaClient {
    @Value("${nasa.api.url}")
    private String apiUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    private static final String SUB_URL = "/EPIC/api/natural/date/";

    private final RestTemplateBuilder restTemplateBuilder;

    private final HttpHeaders headers;


    public MetaClient() {
        this.restTemplateBuilder = new RestTemplateBuilder();

        this.headers = new HttpHeaders();
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    public List<Meta> get(LocalDate date) {
        String url = apiUrl + SUB_URL + date.toString() + "?api_key=" + apiKey;

        ResponseEntity<List<Meta>> res = this.restTemplateBuilder.build().exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(null, this.headers),
                new ParameterizedTypeReference<List<Meta>>() {}
        );

        return res.getBody();
    }
}
