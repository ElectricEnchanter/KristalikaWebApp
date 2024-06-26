package com.kristalika.app.repo;

import com.kristalika.app.models.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM Appointment WHERE date = ?1 AND master_id = ?2 ORDER BY time", nativeQuery = true)
    Iterable<Appointment> findAppointmentByDateAndId(String date, Long id);

    @Query(value = "SELECT id FROM Appointment WHERE date = ?1 AND master_id = ?2 ORDER BY time", nativeQuery = true)
    ArrayList<Integer> findIdByDateAndId(String date, Long id);

    @Query(value = "SELECT * FROM appointment WHERE NOT EXISTS( SELECT 1 FROM clients WHERE appointment.id = clients.appointment_id) AND appointment.date = ?1 AND appointment.master_id = ?2", nativeQuery = true)
    Iterable<Appointment> findAppointmentNotExist(String date, Long id);

    @Query(value = "SELECT appointment.master_id FROM appointment WHERE id = ?1", nativeQuery = true)
    Long findIdByAppointments(Long id);

    @Query(value = "SELECT name FROM masters JOIN appointment ON masters.id = ?1 LIMIT 1", nativeQuery = true)
    String findMasterNameById(Long id);

//    @Query(value = "SELECT  clients.id , CLIENTS.client_name, CLIENTS.info, clients.note, a.time, a.date FROM CLIENTS JOIN appointment a on a.id = CLIENTS.appointment_id WHERE a.date = ?1", nativeQuery = true)
//    Iterable<Appointment> findClientsAppointmentsByDate(String date);

}
