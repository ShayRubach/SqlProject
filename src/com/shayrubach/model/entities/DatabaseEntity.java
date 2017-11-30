package com.shayrubach.model.entities;
import com.shayrubach.annotations.*;

import java.util.UUID;

@PerformsLogic
public abstract class DatabaseEntity {
    protected String id = null;

    public DatabaseEntity(){
        setId(generateUUID());
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    @SavedInLocalTextFile
    private String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid.replace("-","");
        return uuid.substring(0,8);
    }


}
