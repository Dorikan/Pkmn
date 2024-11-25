package ru.mirea.pkmn.repository;

import ru.mirea.pkmn.entities.Student;

import java.util.List;
import java.util.UUID;

public interface StudentRepo {

    Student save(Student entity);

    Student findById(UUID id);

    Student findByFullName(String firstName, String familyName, String surName);

    Student findExceptOfId(Student student);

    boolean studentExists(Student student);
}
