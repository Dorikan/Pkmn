package ru.mirea.pkmn.repository;

import jakarta.persistence.EntityManager;
import ru.mirea.pkmn.entities.*;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class StudentRepoImpl implements StudentRepo{
    private final EntityManager entityManager;

    @Override
    public Student save(Student entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Student findById(UUID id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student findByFullName(String firstName, String familyName, String surName) {
        return entityManager.createQuery("""
            select s from Student s
            where s.firstName = :firstName
              and s.familyName = :familyName
              and s.surName = :surName
        """, Student.class)
                .setParameter("firstName", firstName)
                .setParameter("familyName", familyName)
                .setParameter("surName", surName)
                .getResultList().getFirst();
    }

    @Override
    public boolean studentExists(Student student) {
        return !entityManager.createQuery("""
                            select s from Student s
                            where s.firstName = :firstName
                              and s.familyName = :familyName
                              and s.surName = :surName
                              and s.group = :group
                        """, Student.class)
                .setParameter("firstName", student.getFirstName())
                .setParameter("familyName", student.getFamilyName())
                .setParameter("surName", student.getSurName())
                .setParameter("group", student.getGroup())
                .getResultList().isEmpty();
    }
}
