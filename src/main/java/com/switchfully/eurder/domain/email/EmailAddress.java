package com.switchfully.eurder.domain.email;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.switchfully.eurder.domain.address.Address;

import java.util.Objects;

@JsonSerialize
public class EmailAddress {
    @JsonProperty("username")
    private final String username;
    @JsonProperty("domain")
    private final String domain;

    public EmailAddress(String username, String domain) {
        this.username = username;
        this.domain = domain;
    }

    @Override
    public String toString() {
        return this.username + "@" + this.domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(username, that.username) && Objects.equals(domain, that.domain);
    }
}
