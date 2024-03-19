package com.mail.repositories;

import com.mail.models.Friendship;
import com.mail.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendshipRepository {
    private Session session = null;

    public FriendshipRepository() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }

    public List<Friendship> findAll() {
        Query<Friendship> query = session.createQuery("FROM friendship", Friendship.class);
        return query.getResultList();
    }

    public Friendship findFriendshipId(Long id) {
        try {
            return session.get(Friendship.class, id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean createFriendship(Friendship friendship) {
        try {
            session.getTransaction().begin(); //начинаем транзакцию
            session.persist(friendship); //метод для сохранения
            session.getTransaction().commit();//коммиты
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean updateFirstUser(User firstUser, Long id) {
        try {
            Friendship friendship = session.get(Friendship.class, id);
            friendship.setFirstUserId(firstUser.getId());
            session.getTransaction().begin();
            session.merge(friendship); //update
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean updateSecondUser(User secondUser, Long id) {
        try {
            Friendship friendship = session.get(Friendship.class, id);
            friendship.setSecondUserId(secondUser.getId());
            session.getTransaction().begin();
            session.merge(friendship); //update
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public Boolean deleteFriendship(Long id) {
        try {
            session.getTransaction().begin();
            session.remove(session.get(Friendship.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }
}
