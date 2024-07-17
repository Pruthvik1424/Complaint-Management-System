package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.ProfileImageUploadDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

@Repository
@Slf4j
public class ImageUploadRepoImpl implements ImageUploadRepo {

    static final Logger log = LoggerFactory.getLogger(ImageUploadRepoImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public boolean saveImage(ProfileImageUploadDto profileImageUploadDto) {
        log.info("saveImage method running in ImageUploadRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(profileImageUploadDto);
            entityTransaction.commit();
            log.info("Saved profile image for user: " + profileImageUploadDto);
            return true; // Return true on successful commit
        } catch (Exception e) {
            log.error("Error saving image: {}", e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            return false; // Return false on failure
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }
    }


    //foreign key set
    @Override
    public Optional<ProfileImageUploadDto> findByUserId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            String query = "SELECT p FROM ProfileImageUploadDto p WHERE p.id = :id";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("id", id);
            return query1.getResultList().stream().findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
            return Optional.empty();
        } finally {
            entityManager.close(); // Ensure the EntityManager is closed after the operation
        }
    }

    @Override
    public void imageUpdateDetails(ProfileImageUploadDto profileImageUploadDto) {

        log.info("updateImage method running in ImageUploadRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(profileImageUploadDto);
            entityTransaction.commit();
        } catch (Exception e) {
            log.error("Error updating image: {}", e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
            log.info("Connection closed");
        }


    }

}



//    @Override
//    public void SetAllImagesInactiveForUser(int id) {
//        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
//            EntityTransaction entityTransaction = entityManager.getTransaction();
//
//            try {
//                entityTransaction.begin();
////                    String query = "UPDATE EditProfileImageDTO e SET e.status = 'Inactive' WHERE e.user.id = :userId";
//
//                String query = "UPDATE ProfileImageUploadDto  SET status = 'Inactive' WHERE user.id = :userId";
//
//                Query updateQuery = entityManager.createQuery(query);
//                updateQuery.setParameter("userId", id);
//                int updatedCount = updateQuery.executeUpdate();
//
//                log.info("Number of images set inactive: {}", updatedCount);
//                entityTransaction.commit();
//            } catch (Exception e) {
//                log.error("Error setting images inactive for user with ID {}: {}", id, e.getMessage());
//                if (entityTransaction != null && entityTransaction.isActive()) {
//                    entityTransaction.rollback();
//                }
//            } finally {
//                entityManager.close();
//                log.info("Connection closed for SetAllImagesInactiveForUser..");
//            }
//        }
//    }
//}
