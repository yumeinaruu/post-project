package com.mail.repositories;

import com.mail.models.Security;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityRepository {
    private Session session = null;

    public SecurityRepository() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }

    public List<Security> findAll() {
        Query<Security> query = session.createQuery("FROM security", Security.class);
        return query.getResultList();
    }

    public Security findSecurityId(Long id) {
        try {
            return session.get(Security.class, id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean createSecurity(Security security) {
        try {
            session.getTransaction().begin(); //начинаем транзакцию
            session.persist(security); //метод для сохранения
            session.getTransaction().commit();//коммиты
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean updateLogin(String newLogin, Long id) {
        try {
            Security security = session.get(Security.class, id);
            security.setLogin(newLogin);
            session.getTransaction().begin();
            session.merge(security); //update
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean updatePassword(String newPassword, Long id) {
        try {
            Security security = session.get(Security.class, id);
            security.setPassword(newPassword);
            session.getTransaction().begin();
            session.merge(security); //update
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }
    public Boolean deleteSecurity(Long id) {
        try {
            session.getTransaction().begin();
            session.remove(session.get(Security.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }
}
