package com.techflow.ws.sys.dto;

/** Login credentials, received in the request body (never in the URL). */
public record LoginRequestDTO(String username, String password) {
}
