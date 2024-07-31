//package com.xworkz.complaintManagementSystem.model.repo;
//
//import com.xworkz.complaintManagementSystem.dto.ComplaintDto;
//import com.xworkz.complaintManagementSystem.dto.SignUpDto;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.*;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@Slf4j
//public class UserRepoImpl implements UserRepo {
//
//    private static final Logger log = LoggerFactory.getLogger(ImageUploadRepoImpl.class);
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//    public UserRepoImpl() {
//        log.info("Created UserRepoImpl..");
//    }
//
//
//    @Override
//    public boolean saveComplaintRaise(ComplaintDto complaintDto) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        try {
//            entityTransaction.begin();
//
//            // Check if signUpDto is already persisted
//            SignUpDto signUpDto = complaintDto.getSignUpDto();
//            if (signUpDto != null && signUpDto.getId() == 0) {
//                entityManager.persist(signUpDto);
//            }
//
//
//            entityManager.persist(complaintDto);
//            entityTransaction.commit();
//
//        } catch (PersistenceException persistenceException) {
//            persistenceException.printStackTrace();
//            entityTransaction.rollback();
//         log.error("not saved");
//        } finally {
//            entityManager.close();
//            log.info("connection is closed");
//        }
//        return true;
//    }
//
//    //to set foreign key
//    @Override
//    public Optional<ComplaintDto> findByUserId(int id) {
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            TypedQuery<ComplaintDto> query = entityManager.createQuery(
//                    "SELECT i FROM ComplaintDto i WHERE i.complaint_id = :id", ComplaintDto.class);
//            query.setParameter("id", id);
//
//            return query.getResultList().stream().findFirst();
//        } finally {
//            entityManager.close();
//        }
//
//    }
//
//    @Override
//    public List<ComplaintDto> getComplaintRaisedDetails(String  email) {
//        return Collections.emptyList();
//    }
//
//}