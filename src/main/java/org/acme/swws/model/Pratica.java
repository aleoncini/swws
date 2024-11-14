package org.acme.swws.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="pratiche")
public class Pratica  extends PanacheMongoEntity {
    
    public String status;
    public String pec;
    public String fonte;
    public String note;
    public String operatoreEconomico;
    public String profilo;
    public int approved; // 0 = not processed; 1 = approved; -1 = refused
    public Long date;

    public Pratica(){
        this.status = "NEW";  // means NEW
        this.date = System.currentTimeMillis();
    }

    public static Pratica findById(String id){
        return find("id", id).firstResult();
    }

}
