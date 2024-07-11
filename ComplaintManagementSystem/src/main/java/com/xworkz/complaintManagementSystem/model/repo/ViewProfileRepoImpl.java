package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ViewProfileRepoImpl implements ViewProfileRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ViewProfileRepoImpl() {
        System.out.println("Running ViewProfileRepoImpl...");
    }

    @Override
    public SignUpDto findByUserEmail(String email) {
        System.out.println("Running findByUserEmail method in ViewProfileRepoImpl...");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT v FROM SignUpDto v WHERE v.email =:email";
            Query q1 = entityManager.createQuery(query);
            q1.setParameter("email", email);

            SignUpDto singleResult = (SignUpDto) q1.getSingleResult();
            entityTransaction.commit();
            return singleResult;
        } catch (NoResultException noResultException) {
            System.out.println("No user found with email: " + email);
            entityTransaction.rollback();
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }


    @Override
    public SignUpDto updateEditedUser(SignUpDto signUpDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            SignUpDto existingUser = entityManager.find(SignUpDto.class, signUpDto.getId());
            if (existingUser != null) {
                System.out.println("Updating user: " + existingUser);
                // Update the user details
                existingUser.setFname(signUpDto.getFname());
                existingUser.setLname(signUpDto.getLname());
                existingUser.setMobilenumber(signUpDto.getMobilenumber());
                existingUser.setAlternatemobilenumber(signUpDto.getAlternatemobilenumber());
                existingUser.setAddress(signUpDto.getAddress());

                // Persist the changes
                entityManager.merge(existingUser);
                entityTransaction.commit();
                System.out.println("User updated: " + existingUser);
                return existingUser;
            } else {
                System.out.println("No user found to update: " + signUpDto.getEmail());
                return null; // no user found
            }
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }
}
