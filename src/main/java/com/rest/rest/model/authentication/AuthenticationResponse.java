package com.rest.rest.model.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthenticationResponse {
    @JsonProperty("access_Token")
    private final String accessToken;

    @JsonProperty("refresh_Token")
    private final String refreshToken;


}
