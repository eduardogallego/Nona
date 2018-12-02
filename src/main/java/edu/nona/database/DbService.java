package edu.nona.database;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class DbService {

    @PersistenceContext
    private EntityManager entityManager;
}
