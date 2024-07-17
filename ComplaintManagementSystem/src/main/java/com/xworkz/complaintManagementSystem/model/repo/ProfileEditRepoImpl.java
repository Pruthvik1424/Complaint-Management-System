package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class ProfileEditRepoImpl implements ProfileEditRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public ProfileEditRepoImpl() {
        System.out.println("No parameters in ProfileEditRepoImpl..");
    }

    @Override
    public SignUpDto findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT e From SignUpDto e WHERE e.email=:email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);

            // Use getResultList() instead of getSingleResult()
            List<SignUpDto> results = query1.getResultList();

            if (!results.isEmpty()) {
                return results.get(0);// Return the first result if found
            }

        } catch (NoResultException e) {
            // Handle case where no results are found
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return null;// Return null if no results found

    }

    @Override
    public void updateUserDetails(SignUpDto signUpDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(signUpDto); // Ensure signUpDTO is managed or retrieved first
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }

    }
}
