package com.agile.test.dao.impl;

import com.agile.test.dao.ImageDao;
import com.agile.test.model.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Image> pictureCriteriaQuery = criteriaBuilder.createQuery(Image.class);
            Root<Image> root = pictureCriteriaQuery.from(Image.class);
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                predicates.add(root.get(entry.getKey()).in(Arrays.asList(entry.getValue())));
            }
            pictureCriteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
            return session.createQuery(pictureCriteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get the list of images", e);
        }
    }

    @Override
    public void clear() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Image").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Couldn't clear Images");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
