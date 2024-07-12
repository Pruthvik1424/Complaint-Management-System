package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class SignUpRepoImpl implements SignUpRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(SignUpDto signUpDto) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(signUpDto);
            entityTransaction.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
            return false;
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public SignUpDto findByEmailAndPassword(String email, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select c from SignUpDto c where email=:email and password=:pass");
            query.setParameter("email", email);
            query.setParameter("pass", password);

            List<SignUpDto> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                return null;
            } else if (resultList.size() == 1) {
                return resultList.get(0);
            } else {
                throw new NonUniqueResultException("Multiple results found for email: " + email);
            }
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public SignUpDto findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction entityTransaction =  entityManager.getTransaction();
      entityTransaction.begin();
        try {
            System.out.println("Querying for email: " + email);
            Query query = entityManager.createQuery("select s from SignUpDto s where s.email = :email");
            query.setParameter("email", email);
            List<SignUpDto> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                System.out.println("No results found for email: " + email);
                entityTransaction.commit();
                return null;
            }
            return resultList.get(0);
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean update(SignUpDto signUpDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.merge(signUpDto);
            tx.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public SignUpDto existByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select s from SignUpDto s where s.email = :email");
            query.setParameter("email", email);
            System.out.println("Running existByEmail method in signup repoimpl");
            List<SignUpDto> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                return null;
            }
            return resultList.get(0); // Return the first result if multiple found
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public SignUpDto existByMobilenumber(Long mobilenumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query q1 = entityManager.createQuery("select s from SignUpDto s where s.mobilenumber = :mobilenumber");
            q1.setParameter("mobilenumber", mobilenumber); // Corrected parameter name
            System.out.println("Running existByMobilenumber method in signup repo impl");
            List<SignUpDto> resultlist = q1.getResultList();
            if (resultlist.isEmpty()) {
                return null;
            }
            return resultlist.get(0);
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean resetPasswordByEmail(SignUpDto signUpDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        boolean updateStatus = false;

        try {
            tx.begin();
            Query query = entityManager.createQuery(
                    "UPDATE SignUpDto c SET c.password = :password WHERE c.email = :email");
            query.setParameter("password", signUpDto.getPassword());
            query.setParameter("email", signUpDto.getEmail());
            int updatedCount = query.executeUpdate();
            tx.commit();
            updateStatus = updatedCount > 0;
        } catch (PersistenceException e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            entityManager.close();
        }

        return updateStatus;
    }

}
