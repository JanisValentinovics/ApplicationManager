package lv.team3.botcovidlab.manager.service.entityManager;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    public static final String COL_NAME = "patients";
    private static DocumentReference documentReference;
    private static ApiFuture<DocumentSnapshot> future;
    private static CollectionReference collectionReference;
    public static boolean isPatientFound(String personalCode) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        documentReference = dbFirestore.collection(COL_NAME).document(personalCode);
        future = documentReference.get();
        DocumentSnapshot document = future.get();
        return document.exists();
    }

    public static String savePatientDetails(Patient patient) throws InterruptedException, ExecutionException {
        String personalCode = patient.getPersonalCode();
        if (isPatientFound(personalCode)) {
            return "Patient with personal code " + personalCode + " already exists in the database";
        } else {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("patients")
                    .document(personalCode)
                    .set(patient);
            System.out.println("Patient with personal code " + personalCode + " added to database");
            return collectionsApiFuture.get().getUpdateTime().toString();
        }
    }

    public static Patient createPatient(Long chatId, String name, String lastName,
                                        String personalCode, String temperature, boolean isContactPerson,
                                        boolean hasCough, boolean hasTroubleBreathing,
                                        boolean hasHeadache, String phoneNumber) throws ExecutionException, InterruptedException {

        if (!isPatientFound(personalCode)) {
            try {
                Patient patient = new Patient();
                patient.setChatId(chatId);
                patient.setName(name);
                patient.setLastName(lastName);
                patient.setPersonalCode(personalCode);
                patient.setTemperature(temperature);
                patient.setContactPerson(isContactPerson);
                patient.setHasCough(hasCough);
                patient.setHasTroubleBreathing(hasTroubleBreathing);
                patient.setHasHeadache(hasHeadache);
                patient.setPhoneNumber(phoneNumber);
                savePatientDetails(patient);
                return patient;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("Patient with personal code " + personalCode + " already exists in the database");
            return null;
        }
    }


    public static Patient getPatientDetails(String personalCode) throws InterruptedException, ExecutionException {
        Patient patient = null;
        if (isPatientFound(personalCode)) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            documentReference = dbFirestore.collection(COL_NAME).document(personalCode);
            future = documentReference.get();
            DocumentSnapshot document = future.get();
            patient = document.toObject(Patient.class);
        } else {
            System.out.println("Patient with personal code " + personalCode + " not found");
        }
        return patient;
    }
    public static List<Patient> getAllPatientsDetails() throws InterruptedException, ExecutionException {
        ArrayList<Patient> arrayList = new ArrayList<>();

            Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future =
                db.collection("patients").get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            System.out.println(document.toObject(Patient.class).toString());
            arrayList.add(document.toObject(Patient.class));

        }

        return arrayList;
    }
    public static String updatePatientDetails(Patient patient) throws InterruptedException, ExecutionException {
        String personalCode = patient.getPersonalCode();
        if (isPatientFound(personalCode)) {
            deletePatient(patient.getPersonalCode());
            savePatientDetails(patient);
            System.out.println("Patient:"+patient.getPersonalCode()+" updated!");
            return "Patient:"+patient.getPersonalCode()+" updated!";
        } else {
            System.out.println("Patient with personal code " + personalCode + " not found");
            return null;
        }
    }

    public static String deletePatient(String personalCode) throws ExecutionException, InterruptedException {
        if (isPatientFound(personalCode)) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            documentReference = dbFirestore.collection(COL_NAME).document(personalCode);
            documentReference.delete();
            return "Document with Patient ID " + personalCode + " has been deleted";
        } else {
            System.out.println("Patient with personal code " + personalCode + " not found");
            return null;
        }
    }

    public static Patient findByChatId(Long chatId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> patientQuery = dbFirestore.collection(COL_NAME).whereEqualTo("chatId", chatId).get();
        List<QueryDocumentSnapshot> entry = patientQuery.get().getDocuments();
        if(entry.isEmpty()) {
            System.out.println("Patient with chatId " + chatId + " not found");
            return null;
        } else {
            System.out.println("Patient with chatId "+ chatId + " found!");
            return entry.get(0).toObject(Patient.class);
        }
    }

    public static Patient findByPersonaId(String personalId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> patientQuery = dbFirestore.collection(COL_NAME).whereEqualTo("personalCode", personalId).get();
        List<QueryDocumentSnapshot> entry = patientQuery.get().getDocuments();
        if(entry.isEmpty()) {
            System.out.println("Patient with PersonalId " + personalId + " not found");
            return null;
        } else {
            System.out.println("Patient with chatId "+ personalId + " found!");
            return entry.get(0).toObject(Patient.class);
        }

    }
}
