package org.acme.swws.model;

public class Subscription {
    public String type;
    public String callbackUrl;
    public String getType() {
        return type;
    }
    public Subscription setType(String type) {
        this.type = type;
        return this;
    }
    public String getUrl() {
        return callbackUrl;
    }
    public Subscription setUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }    
}
