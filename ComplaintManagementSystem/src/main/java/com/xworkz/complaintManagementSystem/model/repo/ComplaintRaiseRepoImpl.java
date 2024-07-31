package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.ComplaintDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.xworkz.complaintManagementSystem.model.repo.ImageUploadRepoImpl.log;


@Repository
public class ComplaintRaiseRepoImpl implements ComplaintRaiseRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ComplaintRaiseRepoImpl() {
        System.out.println("Created RaiseComplaintRepoImpl");
    }

    @Override
    public boolean saveRaiseComplaintType(ComplaintDto complaintDto) {
        System.out.println("Running saveRaiseComplaintType method in RaiseComplaintRepoImpl ");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(complaintDto);
            entityTransaction.commit();
        } catch (PersistenceException persistenceException) {

            persistenceException.getStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public Optional<ComplaintDto> findByUserId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ComplaintDto> query = entityManager.createQuery(
                    "SELECT i FROM ComplaintDto i WHERE i.userid = :id", ComplaintDto.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ComplaintDto> getComplaintsByUserId(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ComplaintDto> query = entityManager.createQuery(
                    "SELECT r FROM ComplaintDto r WHERE r.userid.id = :userId", ComplaintDto.class);
            query.setParameter("userId", userId);
            List<ComplaintDto> results = query.getResultList();
            System.out.println("Found {} complaints for user ID {}"+ results.size()+ userId);
            return results;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<ComplaintDto> findByComplaintId(int complaintId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ComplaintDto> query = entityManager.createQuery(
                    "SELECT r FROM ComplaintDto r WHERE r.complaintId = :complaintId", ComplaintDto.class);
            query.setParameter("complaintId", complaintId);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ComplaintDto updateRaiseComplaintUserDetails(ComplaintDto complaintDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try
        {
            entityTransaction.begin();
            entityManager.merge(complaintDto);
            entityTransaction.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityTransaction.rollback();
        }
        finally {
            entityManager.close();
            log.info("updateRaiseComplaintUserDetails connection closed");
        }

        return complaintDto;
    }


}
