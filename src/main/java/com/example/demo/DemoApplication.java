package com.example.demo;

import ch.qos.logback.core.joran.spi.ConsoleTarget;
import com.example.demo.entities.Patient;
import java.util.*;
import com.example.demo.repositories.PatientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;


@SpringBootApplication
public class DemoApplication<List> implements CommandLineRunner {
@Autowired
    private PatientRepositories patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i=0; i<100 ;i++){
            patientRepository.save(
                    new Patient(null, "Med", new Date(), Math.random() > 0.5 ? true : false, (int)(Math.random() * 100))
    );
        }
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,5));
        System.out.print("Total pages "+patients.getTotalPages());
        System.out.print(" Total Elements "+patients.getTotalElements());
        System.out.print(" Number Page :"+patients.getNumber());
        java.util.List<Patient> content = patients.getContent();
        Page<Patient> byMalade = patientRepository.findByMalade(true, PageRequest.of(0, 3));
        java.util.List<Patient> patientList = patientRepository.chercherPatient(new Date(), new Date(), "%h%");
        patientRepository.save(new Patient(null,"ahmed",new Date(),false,56));
        patientRepository.save(new Patient(null,"ayoub",new Date(),true,40));
        patientRepository.save(new Patient(null,"tarik",new Date(),false,60));
        Patient patient = patientRepository.findById(1L).orElse(null);
        if(patient != null){
            System.out.print(patient.getId());
            System.out.print(  patient.getNom());
            System.out.print(patient.getScore());
            System.out.print(patient.getDateNaissance());
            System.out.print(patient.isMalade());

        }
        patient.setScore(870);
        patientRepository.save(patient);
        patientRepository.deleteById(1L);
        java.util.List<Patient> patientss = patientRepository.findAll();
        patientss.forEach(p->{
            System.out.print("-------------------------");
            System.out.print(p.getId());
            System.out.print(" ");
            System.out.print(p.getNom());
            System.out.print(" ");
            System.out.print(p.getScore());
            System.out.print(" ");
            System.out.print(p.getDateNaissance());
            System.out.print(" ");
            System.out.print(p.isMalade());
        });
        patientRepository.findAll().forEach(p->{
            System.out.println(p.toString());
        });
    }
}
