package pe.com.cma.apiaccess.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.cma.apiaccess.dao.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
