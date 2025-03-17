package com.example.Hari.Mapping.CriteriaBuilder;


import com.example.Hari.Mapping.Entity.User;
import com.example.Hari.Mapping.Entity.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepo {

    private  final EntityManager entityManager ;




    public List<User> findByNameAndRole(String name, String role, String jerseyNo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        // select * from User
        Root<User> root = criteriaQuery.from(User.class);
        //where name like '% rohit %'
        Predicate namepredicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
        Predicate rolePredicate = criteriaBuilder.like(root.get("role"), "%" + role + "%");
        Predicate jerseyPredicate = criteriaBuilder.like(root.get("jerseyNo"), "%" + jerseyNo + "%");

        Predicate nameOrRole = criteriaBuilder.or(namepredicate, rolePredicate);


        // Final Query => Select * from User where name like '%rohit%' or role '%batsmen%' and jerseyNo like'%jerseyNo%
        // criteriaQuery.where(orPredicate);


        Predicate andJerseyNo = criteriaBuilder.and(nameOrRole, jerseyPredicate);
        criteriaQuery.where(andJerseyNo);

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();

    }


    public List<User> findByNameAndRole(UserModel userModel) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);


        Root<User> root = criteriaQuery.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (!(userModel.getRole() == null)) {
            predicates.add(criteriaBuilder.like(root.get("role"), "%" + userModel.getRole() + "%"));
        }

        if (!(userModel.getName() == null)) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + userModel.getName() + "%"));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        TypedQuery<User> resultList = entityManager.createQuery(criteriaQuery);
        return resultList.getResultList();


    }


//    public List<User> findByName(String name) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//        //select * from User where name like '%hari%;
//        Root<User> afcsRoot = criteriaQuery.from(User.class);
//        Predicate namePredicate = criteriaBuilder.like(afcsRoot.get("name"),"%" + name + "%");
//       return entityManager.createQuery(criteriaQuery.where(namePredicate)).getResultList();
//    }


    public List<User> findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        //select * from User where name like '%hari%;
        Root<User> afcsRoot = criteriaQuery.from(User.class);
        Predicate namePredicate = criteriaBuilder.equal(afcsRoot.get("name"),name);
       // Predicate namePredicate = criteriaBuilder.like(afcsRoot.get("name"),"%" + name + "%");
        return entityManager.createQuery(criteriaQuery.where(namePredicate)).getResultList();
    }

    public List<User> findByNameAndRoles(String name,String role) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        //select * from User where name like '%hari%;
        Root<User> afcsRoot = criteriaQuery.from(User.class);
        Predicate namePredicate = criteriaBuilder.like(afcsRoot.get("name"),"%" + name + "%");
        Predicate rolePredicate = criteriaBuilder.like(afcsRoot.get("role"),"%" + role + "%");

        criteriaQuery.where(criteriaBuilder.and(namePredicate,rolePredicate));
      //  criteriaQuery.where(criteriaBuilder.or(namePredicate,rolePredicate));
        // Predicate namePredicate = criteriaBuilder.like(afcsRoot.get("name"),"%" + name + "%");
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
