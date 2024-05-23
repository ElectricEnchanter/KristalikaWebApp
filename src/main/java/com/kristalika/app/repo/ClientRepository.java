package com.kristalika.app.repo;

import org.springframework.data.repository.CrudRepository;
import com.kristalika.app.models.Clients;

public interface ClientRepository extends CrudRepository<Clients, Long> {
}
