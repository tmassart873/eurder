package com.switchfully.eurder.domain.email;

public class EmailAddress {
    private final String username;
    private final String domain;

    public EmailAddress(String username, String domain) {
        this.username = username;
        this.domain = domain;
    }

    public String getFullEmailAddress(){
        return this.username + "@" + this.domain;
    }
}
