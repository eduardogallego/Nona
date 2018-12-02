package edu.nona.database;

import edu.nona.entity.Bracelet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BraceletRepository extends JpaRepository<Bracelet, String> {
}
