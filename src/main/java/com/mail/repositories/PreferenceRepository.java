package com.mail.repositories;

import com.mail.models.Preference;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PreferenceRepository {
    private Session session = null;

    public PreferenceRepository() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }

    public List<Preference> findAll() {
        Query<Preference> query = session.createQuery("FROM preferences ", Preference.class);
        return query.getResultList();
    }

    public Preference findPreferenceId(Long id) {
        try {
            return session.get(Preference.class, id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean createPreference(Preference preference) {
        try {
            session.getTransaction().begin(); //начинаем транзакцию
            session.persist(preference); //метод для сохранения
            session.getTransaction().commit();//коммиты
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public void updatePreferences(Preference preference) {
        try {
            session.beginTransaction();
            session.merge(preference);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
    }

    public Boolean deletePreference(Long id) {
        try {
            session.getTransaction().begin();
            session.remove(session.get(Preference.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }
}
