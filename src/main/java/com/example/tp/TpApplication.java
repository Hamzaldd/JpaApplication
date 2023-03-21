package com.example.tp;

import com.example.tp.entities.Patient;
import com.example.tp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TpApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(TpApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        for (int i= 0;i <25 ; i++){
            patientRepository.save(new Patient(null,"Hamza",new Date(),true,(int)(Math.random()*100)));
            patientRepository.save(new Patient(null,"Hassan",new Date(),false,(int)(Math.random()*100)));
            patientRepository.save(new Patient(null,"Hajar",new Date(),false,(int)(Math.random()*100)));
            patientRepository.save(new Patient(null,"Imane",new Date(),true,(int)(Math.random()*100)));
        }
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,5));
        List<Patient> content = patients.getContent();
        List<Patient> byMalade = patientRepository.findByMalade(true);
        Page<Patient> byMaladePage = patientRepository.findByMalade(true,PageRequest.of(0,5));
        List<Patient> patientlist = patientRepository.cherchePatient("%H%",20);

        patientlist.forEach(p->{
            System.out.println("==========================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });
        System.out.println("==========================");
        Patient patient = patientRepository.findById(1L).orElse(null);
        if (patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }


    }
}
