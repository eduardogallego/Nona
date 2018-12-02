package edu.nona.database;

import edu.nona.entity.Bracelet;
import edu.nona.entity.Carer;
import edu.nona.entity.Poll;
import edu.nona.entity.Smartphone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DbInitializer.class);

    @Autowired
    private BraceletRepository braceletRepository;
    @Autowired
    private CarerRepository carerRepository;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private SmartphoneRepository smartphoneRepository;


    @Override
    public void run(String... args) throws Exception {
        Carer carer = new Carer("eduardo@nona.com", "eduardo");
        carerRepository.save(carer);
        carerRepository.save(new Carer("antonio@nona.com", "antonio"));
        Bracelet bracelet = new Bracelet("brc001", "hwv001", "fwv001", 300);
        braceletRepository.save(bracelet);
        smartphoneRepository.save(new Smartphone("smp001", carer, "Apple",
                "iPhone 6", "iOS", "11", "ptk001"));
        pollRepository.save(new Poll(bracelet,37, 24,
                100000000l, 100000000l, 85, 95));
        pollRepository.save(new Poll(bracelet,37, 24,
                100000000l, 100000000l, 85, 95));
        pollRepository.save(new Poll(bracelet,37, 24,
                100000000l, 100000000l, 85, 95));
        log.info("Database Initialized");
    }
}
