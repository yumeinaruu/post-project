package com.mail.repositories;

import com.mail.models.LetterCondition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LetterConditionRepository {
    private Session session = null;

    public LetterConditionRepository() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }

    public List<LetterCondition> findAll() {
        Query<LetterCondition> query = session.createQuery("FROM letter_condition", LetterCondition.class);
        return query.getResultList();
    }

    public LetterCondition findLetterConditionId(Long id) {
        try {
            return session.get(LetterCondition.class, id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean createLetterCondition(LetterCondition letterCondition) {
        try {
            session.getTransaction().begin(); //начинаем транзакцию
            session.persist(letterCondition); //метод для сохранения
            session.getTransaction().commit();//коммиты
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean updateCondition(String newCondition, Long id) {
        try {
            LetterCondition letterCondition = session.get(LetterCondition.class, id);
            letterCondition.setCondition(newCondition);
            session.getTransaction().begin();
            session.merge(letterCondition); //update
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean deleteLetterCondition(Long id) {
        try {
            session.getTransaction().begin();
            session.remove(session.get(LetterCondition.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }
}
