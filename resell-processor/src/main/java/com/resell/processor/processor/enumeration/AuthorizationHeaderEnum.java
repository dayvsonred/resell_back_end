package com.resell.processor.processor.enumeration;

import feign.Headers;
import org.springframework.http.HttpHeaders;

public enum AuthorizationHeaderEnum {

    Authorization;

    public HttpHeaders getHeadersAuth(String BasicBase64, int lengthVal) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "*/*");
        httpHeaders.add("Authorization", "Basic " + BasicBase64);
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        httpHeaders.add("Content-Length", "0");
        return httpHeaders;
    }

    public HttpHeaders getHeadersCart(String Token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json, text/plain, */*");
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer " + Token);

        return httpHeaders;
    }



}
