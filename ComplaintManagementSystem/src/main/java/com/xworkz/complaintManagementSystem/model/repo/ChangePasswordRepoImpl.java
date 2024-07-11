package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class ChangePasswordRepoImpl implements ChangePasswordRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ChangePasswordRepoImpl() {
        System.out.println("Running change password repo impl");
    }

    @Override
    public SignUpDto findByEmailAndPassword(String email, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select s from SignUpDto s where s.email = :email and s.password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            return (SignUpDto) query.getSingleResult();
        } catch (PersistenceException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        boolean updateStatus = false;

        try {
            tx.begin();
            Query query = entityManager.createQuery("UPDATE SignUpDto s SET s.password = :password WHERE s.email = :email");
            query.setParameter("password", newPassword);
            query.setParameter("email", email);
            int updatedRows = query.executeUpdate(

            );
            tx.commit();
            updateStatus = updatedRows > 0;
        } catch (PersistenceException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            entityManager.close();
        }

        return updateStatus;
    }
}
