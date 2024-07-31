package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.DepartmentAdminDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class DepartmentAdminRepoImpl implements DepartmentAdminRepo{

    @Autowired
   private EntityManagerFactory entityManagerFactory;

    public DepartmentAdminRepoImpl(){
        System.out.println("running DepartmentAdminRepoImpl......");
    }

    @Override
    public boolean addDepartmentAdmin(DepartmentAdminDto departmentAdminDto) {
        System.out.println("running addDepartmentAdmin method in DepartmentAdminRepoImpl ");
       EntityManager entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();

      try{
          entityTransaction.begin();
          entityManager.persist(departmentAdminDto);
          entityTransaction.commit();
      }catch (PersistenceException persistenceException){
          persistenceException.printStackTrace();
          entityTransaction.rollback();
      }finally {
          entityManager.close();
          System.out.println("connection is closed ....");
      }

        return true;
    }

    @Override
    public DepartmentAdminDto findByEmailAndPassword(String email, String password) {

       EntityManager entityManager = entityManagerFactory.createEntityManager();
     // EntityTransaction entityTransaction = entityManager.getTransaction();

      try{
          //entityTransaction.begin();
          String query = "Select s from DepartmentAdminDto s where s.email=:email and s.password=:password";
         Query query1 = entityManager.createQuery(query);
         query1.setParameter("email",email);
         query1.setParameter("password",password);

         DepartmentAdminDto result = (DepartmentAdminDto) query1.getSingleResult();

         return result;

//          if (result.isEmpty()) {
//              return null;
//          } else if (result.size() == 1) {
//              return result.get(0);
//          } else {
//              throw new NonUniqueResultException("Multiple results found for email: " + email);
//          }
      } catch (Exception exception){
          exception.printStackTrace();
         return null;
      }
//        catch (NoResultException e) {
//          return null;
//      }
      finally {
          entityManager.close();

      }
    }

    @Override
    public DepartmentAdminDto findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction =  entityManager.getTransaction();
        entityTransaction.begin();
        try {
            System.out.println("Querying for email: " + email);
            Query query = entityManager.createQuery("select s from DepartmentAdminDto s where s.email = :email");
            query.setParameter("email", email);
            List<DepartmentAdminDto> resultList = query.getResultList();
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


}
