package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.ProfileImageUploadDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Optional;

@Repository
public class ViewProfileRepoImpl implements ViewProfileRepo {

    private static final Logger log = LoggerFactory.getLogger(ViewProfileRepoImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public SignUpDto findByUserEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT s FROM SignUpDto s WHERE s.email = :email";
            Query q1 = entityManager.createQuery(query);
            q1.setParameter("email", email);
            return (SignUpDto) q1.getSingleResult();
        } catch (Exception e) {
            log.error("Error finding user by email: " + email, e);
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Transactional
    public SignUpDto updateEditedUser(SignUpDto signUpDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            SignUpDto existingUser = entityManager.find(SignUpDto.class, signUpDto.getId());
            if (existingUser != null) {
                existingUser.setFname(signUpDto.getFname());
                existingUser.setLname(signUpDto.getLname());
                existingUser.setMobilenumber(signUpDto.getMobilenumber());
                existingUser.setAlternatemobilenumber(signUpDto.getAlternatemobilenumber());
                existingUser.setAddress(signUpDto.getAddress());
                entityManager.merge(existingUser);
                entityManager.getTransaction().commit();
                return existingUser;
            } else {
                log.warn("User with id {} not found", signUpDto.getId());
                return null;
            }
        } catch (Exception e) {
            log.error("Error updating user", e);
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Transactional
    public void saveProfileImage(ProfileImageUploadDto profileImageUploadDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(profileImageUploadDto);
            entityManager.getTransaction().commit();
            log.info("Saved profile image for user: " + profileImageUploadDto );
        } catch (Exception e) {
            log.error("Error saving profile image", e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<ProfileImageUploadDto> findByUserId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ProfileImageUploadDto> query = entityManager.createQuery(
                    "SELECT i FROM ProfileImageUploadDto i WHERE i.id = :id", ProfileImageUploadDto.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }
    }
}

