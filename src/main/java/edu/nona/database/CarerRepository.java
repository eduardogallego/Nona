package edu.nona.database;

import edu.nona.entity.Carer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarerRepository extends JpaRepository<Carer, String> {
}
