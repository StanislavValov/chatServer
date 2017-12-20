package com.qaiware.persistence;

import com.qaiware.core.Message;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Stan on 19.12.2017 г..
 */
@Repository
public class PersistentMessageRepository implements MessageRepository {

    @Override
    public void insert(Message message) {
        Session session = null;
        Transaction transaction;

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ChatEntity entity = adapt(message);

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
        catch(HibernateException exception){
            //We can return status from the insertion and bring it to the customer
        }
        finally{
            assert session != null;
            session.close();
        }
    }

    private ChatEntity adapt(Message message) {
        String type = message.getClass().getName();

        return new ChatEntity(type, message.getValue(), new Date());
    }
}
