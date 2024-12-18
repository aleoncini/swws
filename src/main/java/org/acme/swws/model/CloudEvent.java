package org.acme.swws.model;

import java.util.UUID;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="events")
public class CloudEvent extends PanacheMongoEntity{
    public String id;
    public String type;
    public String source;
    public String data;
    public long timestamp;

    public CloudEvent(){
        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
    }
 
    public CloudEvent withType(String type){
        this.type = type;
        return this;
    }

    public CloudEvent withSource(String source){
        this.source = source;
        return this;
    }

    public CloudEvent withData(String data){
        this.data = data;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getData() {
        return data;
    }

}
