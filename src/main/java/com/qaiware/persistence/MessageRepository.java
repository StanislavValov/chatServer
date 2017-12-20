package com.qaiware.persistence;

import com.qaiware.core.Message;

/**
 * Created by Stan on 19.12.2017 г..
 */
public interface MessageRepository {
    void insert(Message message);
}
