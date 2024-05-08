package com.kristalika.app.repo;

import com.kristalika.app.models.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
