package com.example.demo.repositories;

import com.example.demo.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface PatientRepositories extends JpaRepository<Patient, Long> {

    Patient p = new Patient();
    public List<Patient> findByMalade(boolean m);
    public Page<Patient> findByMalade(boolean m, Pageable pageable);
    public List<Patient> findByMaladeAndScoreLessThan(boolean m, int score);
    public List<Patient> findByMaladeIsTrueAndScoreLessThan(int score);
    public List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1, Date d2, String mc);
    @Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z")
    public List<Patient> chercherPatient(@Param("x") Date d1, @Param("y") Date d2, @Param("z") String mc);
}
