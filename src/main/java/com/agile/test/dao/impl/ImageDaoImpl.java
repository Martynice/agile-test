package com.agile.test.dao.impl;

import com.agile.test.dao.ImageDao;
import com.agile.test.model.Image;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl implements ImageDao {
    private final SessionFactory sessionFactory;

    public ImageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Image add(Image image) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(image);
            transaction.commit();
            return image;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Couldn't insert image " + image, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Image> getByParameter(Map<String, String[]> parameters) {
        return null;
    }

    @Override
    public void clearData() {

    }
}
