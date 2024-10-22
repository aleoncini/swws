package org.acme.swws.model;

import java.util.UUID;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="events")
public class CloudEvent extends PanacheMongoEntity{
    public String id;
    public String type;
    public String source;
    public byte[] data;

    public CloudEvent(){
        this.id = UUID.randomUUID().toString();
    }
 
    public CloudEvent withType(String type){
        this.type = type;
        return this;
    }

    public CloudEvent withSource(String source){
        this.source = source;
        return this;
    }

    public CloudEvent withData(byte[] data){
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

    public byte[] getData() {
        return data;
    }

}
