package com.petproject.petproject.service.entityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class FirebaseController {

    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getPatientDetails")
    public Patient getPatient(@RequestParam String personalCode) throws InterruptedException, ExecutionException{
        return firebaseService.getPatientDetails(personalCode);
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients() throws ExecutionException, InterruptedException {
        ArrayList<Patient> patients = (ArrayList<Patient>) FirebaseService.getAllPatientsDetails();

        return patients;
    }

    @PostMapping("/createPatient")
    public String createPatient(@RequestBody Patient patient ) throws InterruptedException, ExecutionException {
        return firebaseService.savePatientDetails(patient);
    }

    @PutMapping("/updatePatient")
    public String updatePatient(@RequestBody Patient patient  ) throws InterruptedException, ExecutionException {
        return firebaseService.updatePatientDetails(patient);
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam String personalCode) throws ExecutionException, InterruptedException {
        return firebaseService.deletePatient(personalCode);
    }


}
