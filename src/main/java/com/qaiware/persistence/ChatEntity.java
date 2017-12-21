package com.qaiware.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Stan on 19.12.2017 г..
 */
@Entity(name = "Messages")
public class ChatEntity {
    @Id
    private int id;

    private String type;
    private String payload;
    private Date createdAt;

    public ChatEntity(String type, String payload, Date createdAt) {
        this.type = type;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public ChatEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
