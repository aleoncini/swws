package org.acme.swws.model;

public class Log {
    public String message;
    public String level;

    public Log setMessage(String message){
        this.message = message;
        return this;
    }

    public Log setLevel(String level){
        this.level = level;
        return this;
    }
}
