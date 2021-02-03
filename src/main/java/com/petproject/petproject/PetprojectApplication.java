package com.petproject.petproject;

import com.petproject.petproject.service.entityManager.FirebaseService;
import com.petproject.petproject.service.entityManager.Patient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@SpringBootApplication

public class PetprojectApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(PetprojectApplication.class, args);

//        Patient patient = new Patient();
//        patient.setPersonalCode("");
//        ArrayList<Patient> patients = (ArrayList<Patient>) FirebaseService.getAllPatientsDetails();
//        for (Patient p : patients){
//            System.out.println(p.toString());
//        }
    }

}
