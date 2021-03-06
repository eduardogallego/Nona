package edu.nona.rest;

import edu.nona.database.BraceletRepository;
import edu.nona.database.CarerRepository;
import edu.nona.database.PollRepository;
import edu.nona.database.SmartphoneRepository;
import edu.nona.entity.Bracelet;
import edu.nona.entity.Carer;
import edu.nona.entity.Poll;
import edu.nona.entity.Smartphone;
import edu.nona.exception.NotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
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

    @PostMapping(path = "/bracelets")
    public ResponseEntity<Object> addBracelet(@RequestBody Bracelet bracelet) {
        Bracelet newBracelet = braceletRepository.save(bracelet);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBracelet.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/carers")
    public ResponseEntity<Object> addCareer(@RequestBody Carer carer) {
        Carer newCarer = carerRepository.save(carer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newCarer.getUsername()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/bracelets/{braceletid}/polls")
    public ResponseEntity<Bracelet> addPoll(@PathVariable String braceletid, @RequestBody Poll poll) {
        Bracelet bracelet = braceletRepository.findById(braceletid)
                .orElseThrow(() -> new NotFoundException("Not Found Bracelet " + braceletid));
        poll.setBracelet(bracelet);
        pollRepository.save(poll);
        return new ResponseEntity<>(bracelet, HttpStatus.CREATED);
    }

    @PostMapping(path = "/bracelets/{braceletid}/pollsold")
    public ResponseEntity<Object> addPollOld(@PathVariable String braceletid, @RequestBody Poll poll) {
        Bracelet bracelet = braceletRepository.findById(braceletid)
                .orElseThrow(() -> new NotFoundException("Not Found Bracelet " + braceletid));
        poll.setBracelet(bracelet);
        Poll newPoll = pollRepository.save(poll);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{pollid}")
                .buildAndExpand(newPoll.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/bracelets/{id}")
    public Resource<Bracelet> getBracelet(@PathVariable String id) {
        Bracelet bracelet = braceletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Bracelet " + id));
        Resource response = new Resource<>(bracelet);
        response.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getBracelets())
                .withRel("all-bracelets"));
        return response;
    }

    @GetMapping("/bracelets/{braceletid}/polls/{pollid}")
    public Poll getBraceletPoll(@PathVariable String braceletid, @PathVariable Long pollid) {
        Poll poll = pollRepository.findById(pollid)
                .orElseThrow(() -> new NotFoundException("Not Found Poll " + pollid));
        if (!poll.getBraceletId().equals(braceletid)) {
            throw new NotFoundException("Not Found Bracelet " + braceletid + " Poll " + pollid);
        }
        return poll;
    }

    @GetMapping("/bracelets/{id}/polls")
    public List<Poll> getBraceletPolls(@PathVariable String id) {
        Bracelet bracelet = braceletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Bracelet " + id));
        return bracelet.getPollList();
    }

    @GetMapping(path = "/bracelets")
    public List<Bracelet> getBracelets() {
        return braceletRepository.findAll();
    }

    @GetMapping("/carers/{username}")
    public Carer getCarer(@PathVariable String username) {
        return carerRepository.findById(username)
                .orElseThrow(() -> new NotFoundException("Not Found Carer " + username));
    }

    @GetMapping(path = "/carers")
    public List<Carer> getCarers() {
        return carerRepository.findAll();
    }

    @GetMapping(path = "/polls/{id}")
    public Poll getPoll(@PathVariable Long id) {
        return pollRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Poll " + id));
    }

    @GetMapping(path = "/polls")
    public List<Poll> getPolls() {
        return pollRepository.findAll();
    }

    @GetMapping("/smartphones/{id}")
    public Smartphone getSmartphone(@PathVariable String id) {
        return smartphoneRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Smartphone " + id));
    }

    @GetMapping(path = "/smartphones")
    public List<Smartphone> getSmartphones() {
        return smartphoneRepository.findAll();
    }
}