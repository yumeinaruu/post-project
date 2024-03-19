package com.mail.repositories;

import com.mail.models.Letter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LetterRepository {
    private Session session = null;

    public LetterRepository() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }

    public List<Letter> findAll() {
        Query<Letter> query = session.createQuery("FROM letters", Letter.class);
        return query.getResultList();
    }

    public Letter findLetterId(Long id) {
        try {
            return session.get(Letter.class, id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean createLetter(Letter letter) {
        try {
            session.getTransaction().begin(); //начинаем транзакцию
            session.persist(letter); //метод для сохранения
            session.getTransaction().commit();//коммиты
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public void updateLetter(Letter letter){
        try{
            session.beginTransaction();
            session.merge(letter);
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
        }
    }

    public Boolean deleteLetter(Long id) {
        try {
            session.getTransaction().begin();
            session.remove(session.get(Letter.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }
}
