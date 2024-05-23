package com.kristalika.app.repo;

import com.kristalika.app.models.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM Appointment WHERE date = ?1 AND master_id = ?2 ORDER BY time", nativeQuery = true)
    Iterable<Appointment> findAppointmentByDate(String date, Long id);

}
