package edu.nona.rest;

import edu.nona.database.BraceletRepository;
import edu.nona.database.CarerRepository;
import edu.nona.database.PollRepository;
import edu.nona.database.SmartphoneRepository;
import edu.nona.entity.Bracelet;
import edu.nona.entity.Carer;
import edu.nona.entity.Poll;
import edu.nona.entity.Smartphone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class BraceletController {

    private final BraceletRepository braceletRepository;
    private final CarerRepository carerRepository;
    private final PollRepository pollRepository;
    private final SmartphoneRepository smartphoneRepository;

    public BraceletController(BraceletRepository braceletRepository, CarerRepository carerRepository,
                              PollRepository pollRepository, SmartphoneRepository smartphoneRepository) {
        this.braceletRepository = braceletRepository;
        this.carerRepository = carerRepository;
        this.pollRepository = pollRepository;
        this.smartphoneRepository = smartphoneRepository;
    }

    @PostMapping(path = "/carers")
    public ResponseEntity<Object> addCareer(@RequestBody Carer carer) {
        Carer newCarer = carerRepository.save(carer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newCarer.getUsername()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/bracelets")
    public List<Bracelet> getBracelets() {
        return braceletRepository.findAll();
    }

    @GetMapping("/carer/{username}")
    public Carer getCarer(@PathVariable String username) {
        return carerRepository.findById(username).orElse(null);
    }

    @GetMapping(path = "/carers")
    public List<Carer> getCarers() {
        return carerRepository.findAll();
    }

    @GetMapping("/carer/{id}")
    public Smartphone getSmartphone(@PathVariable String id) {
        return smartphoneRepository.findById(id).orElse(null);
    }

    @GetMapping(path = "/smartphones")
    public List<Smartphone> getSmartphones() {
        return smartphoneRepository.findAll();
    }

    @GetMapping(path = "/polls")
    public List<Poll> getPolls() {
        return pollRepository.findAll();
    }
    }
