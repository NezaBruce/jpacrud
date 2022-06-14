package Dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class UserDao {
    public void saveUser(User user){
        Transaction transaction=null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start transaction
            transaction=session.beginTransaction();
            //save the student
            session.save(user);
            //commit transaction
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void updateUser(User user){
        Transaction transaction=null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start transaction
            transaction=session.beginTransaction();
            //save the student
            session.update(user);
            //commit transaction
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void deleteUser(int Id){
        Transaction transaction=null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start transaction
            transaction=session.beginTransaction();
            //delete the student
            User user=session.get(User.class,Id);
            if(user!=null){
                session.delete(user);
                System.out.println("User is deleted");
            }
            //commit transaction
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public User getUser(int Id){
        Transaction transaction=null;
        User user=null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start transaction
            transaction=session.beginTransaction();
            //get the student
            user=session.get(User.class,Id);
            //commit transaction
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }
    @SuppressWarnings("unchecked")
    public List<User> getAllUser(){
        Transaction transaction=null;
        List < User > listOfUser = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            //start transaction
            transaction=session.beginTransaction();
            //get the student
            listOfUser=session.createQuery("from User").getResultList();
            //commit transaction
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }
}
