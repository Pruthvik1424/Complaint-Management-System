package com.xworkz.complaintManagementSystem.model.repo;


import com.xworkz.complaintManagementSystem.dto.AddDepartmentsDto;
import com.xworkz.complaintManagementSystem.dto.AdminDto;
import com.xworkz.complaintManagementSystem.dto.ComplaintDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class AdminRepoImpl implements AdminRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    //for admin login
    @Override
    public AdminDto findByEmailAndPassword(String email, String password) {

        System.out.println("findByEmailAndPassword method running in AdminRepoImpl");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();


        try {
            entityTransaction.begin();
            String query = "SELECT f FROM  AdminDto f WHERE  f.email=:AdminEmail AND f.password=:AdminPassword";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("AdminEmail", email);
            query1.setParameter("AdminPassword", password);

            AdminDto data = (AdminDto) query1.getSingleResult();

            System.out.println("Data : "+data);

            entityTransaction.commit();

            return data;

        }
        catch (NoResultException e) {
            System.out.println("No matching user found with the provided email and password.");
            entityTransaction.rollback();
        }

        catch (PersistenceException persistenceException)
        {
          persistenceException.printStackTrace();
          entityTransaction.rollback();
            System.out.println("no result found");
        }

        finally {
            entityManager.close();
            System.out.println("connection is closed");

        }

        return null;

    }

    @Override
    public List<SignUpDto> findById(SignUpDto signUpDto) {
        System.out.println("Running findbyId method in admin repo");
       EntityManager entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction entityTransaction= entityManager.getTransaction();

      try{
          String query = "SELECT d FROM  SignUpDto d";
          Query query1 = entityManager.createQuery(query);
          List<SignUpDto> data = query1.getResultList();
          System.out.println("Data :" + data);
          return data;
      }catch (PersistenceException persistenceException){
          persistenceException.printStackTrace();
         // entityTransaction.rollback();
      }finally {
          entityManager.close();
          System.out.println("connection is closed....");
      }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDto> findByComplaintId(ComplaintDto complaintDto) {
        System.out.println("Running findbyId method in Adminrepo for grtting complaint details");
      EntityManager entityManager =  entityManagerFactory.createEntityManager();

        try{
            String query = "SELECT d FROM  ComplaintDto d";
            Query query1 = entityManager.createQuery(query);
            List<ComplaintDto> complaintDtoList = query1.getResultList();
            System.out.println("complaintDtoList :" + complaintDtoList);
            return complaintDtoList;
        }catch (PersistenceException persistenceException){
            persistenceException.printStackTrace();
            // entityTransaction.rollback();
        }finally {
            entityManager.close();
            System.out.println("connection is closed....");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDto> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("searchByComplaintType method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT c FROM ComplaintDto c WHERE c.city=:City   And c.complaintType = :ComplaintType  ";
//            String query = "SELECT r FROM RaiseComplaintDto r where r.city=:city OR r.complaintType=:complaintTypes";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("ComplaintType", complaintType);
            query1.setParameter("City", city);
            List<ComplaintDto> list = query1.getResultList();
            System.out.println("ComplaintTypeAndCityData: " + list);
            entityTransaction.commit();

            return list;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            System.out.println("Connection closed");
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeAndCity method running in AdminRepoImpl");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT ct FROM ComplaintDto ct WHERE  ct.city =:City OR ct.complaintType =:ComplaintType ";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("ComplaintType", complaintType);
            query1.setParameter("City", city);
            List<ComplaintDto> list = query1.getResultList();
            System.out.println("ListOfTypeOrCity: " + list);
            entityTransaction.commit();

//String query = "SELECT r FROM RaiseComplaintDto r where r.city=:city OR r.complaintType=:complaintTypes";

            return list;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }
        return Collections.emptyList();
    }

    @Override
    public boolean saveDepartments(AddDepartmentsDto addDepartmentsDto) {
       EntityManager entityManager=  entityManagerFactory.createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();
      try{
          entityTransaction.begin();
          entityManager.persist(addDepartmentsDto);
          entityTransaction.commit();
      }catch (PersistenceException persistenceException){
          persistenceException.printStackTrace();
          entityTransaction.rollback();
      }finally {
          entityManager.close();
      }
        return true;
    }

    @Override
    public List<AddDepartmentsDto> getAllDepartments() {
        System.out.println("Running getAllDepartments method in admin repo implementation...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT d FROM AddDepartmentsDto d";
            Query query1 = entityManager.createQuery(query);
            List<AddDepartmentsDto> resultList = query1.getResultList();
            System.out.println("ResultList size: " + resultList.size());
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

//    @Override
//    public void allocateDepartment(int complaintId, int departmentsId, String status) {
//        System.out.println("Running allocateDepartment method in AdminRepoImpl...");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        try {
//            entityTransaction.begin();
//
//            // Find the complaint
//            ComplaintDto complaint = entityManager.find(ComplaintDto.class, complaintId);
//            if (complaint == null) {
//                throw new RuntimeException("Complaint not found for ID: " + complaintId);
//            }
//            System.out.println("Found complaint: " + complaint);
//
//            // Find the department
//            AddDepartmentsDto department = entityManager.find(AddDepartmentsDto.class, departmentsId);
//            if (department == null) {
//                throw new RuntimeException("Department not found for ID: " + departmentsId);
//            }
//            System.out.println("Found department: " + department);
//
//            // Set the department for the complaint
//            complaint.setDepartment(department);
//            complaint.setStatus(status);
//
//            // Merge the updated complaint
//            complaint = entityManager.merge(complaint);
//            System.out.println("Updated complaint after merge: " + complaint);
//
//            entityTransaction.commit();
//            System.out.println("Department allocated successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (entityTransaction.isActive()) {
//                entityTransaction.rollback();
//            }
//        } finally {
//            entityManager.close();
//        }
//
//    }

    @Override
    public void allocateDepartment(int complaintId, int departmentsId,String status) {
        System.out.println("Running allocateDepartment method in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            // Find the complaint
            ComplaintDto complaint = entityManager.find(ComplaintDto.class, complaintId);
            if (complaint == null) {
                throw new RuntimeException("Complaint not found for ID: " + complaintId);
            }
            System.out.println("Found complaint: " + complaint);

            // Find the department
            AddDepartmentsDto department = entityManager.find(AddDepartmentsDto.class, departmentsId);
            if (department == null) {
                throw new RuntimeException("Department not found for ID: " + departmentsId);
            }
            System.out.println("Found department: " + department);

            // Set the department for the complaint
            complaint.setDepartment(department);
            complaint.setStatus(status);

            // Merge the updated complaint
            complaint = entityManager.merge(complaint);
            System.out.println("Updated complaint after merge: " + complaint);

            entityTransaction.commit();
            System.out.println("Department allocated successfully.");
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
