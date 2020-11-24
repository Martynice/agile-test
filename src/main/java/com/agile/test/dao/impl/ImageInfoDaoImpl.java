package com.agile.test.dao.impl;

import com.agile.test.dao.ImageInfoDao;
import com.agile.test.model.ImageInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ImageInfoDaoImpl implements ImageInfoDao {
    private final SessionFactory sessionFactory;

    public ImageInfoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ImageInfo add(ImageInfo imageInfo) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(imageInfo);
            transaction.commit();
            return imageInfo;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Couldn't insert image info " + imageInfo, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
