package com.kristalika.app.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.kristalika.app.models.Clients;

import java.util.ArrayList;

public interface ClientRepository extends CrudRepository<Clients, Long> {

    @Query(value = "SELECT * FROM CLIENTS WHERE appointment_id = ?1 ", nativeQuery = true)
    Iterable<Clients> findAppointmentById(Long appointmentId);

//    @Query(value = "SELECT * FROM CLIENTS WHERE appointment_id = ?1 AND client_name = ?2 AND info = ?3 AND note = ?4", nativeQuery = true)
//    Iterable<Clients> findClientsByAll(Long appointmentId, String name, String info, String note);


}
