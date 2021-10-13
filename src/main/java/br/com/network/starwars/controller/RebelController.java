package br.com.network.starwars.controller;

import br.com.network.starwars.dto.NegotiationDTO;
import br.com.network.starwars.dto.ReportDTO;
import br.com.network.starwars.model.Rebel;
import br.com.network.starwars.model.Report;
import br.com.network.starwars.service.RebelService;
import br.com.network.starwars.util.GenericResponse;
import br.com.network.starwars.vo.LocationVO;
import br.com.network.starwars.vo.RebelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/rebels")
public class RebelController {

    @Autowired
    private RebelService service;

    @Autowired
    private MessageSource messages;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse create(@Valid @RequestBody RebelVO obj) {
        RebelVO created = service.save(obj);

        created.add(linkTo(methodOn(RebelController.class).findById(created.getId())).withSelfRel());
        return new GenericResponse(messages.getMessage("message.rebel.created", null, Locale.getDefault()), created);
    }

    @PatchMapping(value = "/{id}/negotiations")
    public ResponseEntity<String> negociate(@PathVariable("id") Long id, @Valid @RequestBody NegotiationDTO obj) throws Exception {
        String created = service.negociateItems(id, obj);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/reports")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse reportTraitor(@PathVariable("id") Long id, @Valid @RequestBody ReportDTO obj) throws Exception {
        Report created = service.saveReport(id, obj);

        return new GenericResponse(messages.getMessage("message.rebel.reportTraitor", null, Locale.getDefault()), created);
    }

    @PatchMapping(value = "/{id}/location")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse updateLocation(@PathVariable("id") Long id, @Valid @RequestBody LocationVO obj) {
        LocationVO created = service.saveLocation(id, obj);

        return new GenericResponse(messages.getMessage("message.rebel.locationUpdated", null, Locale.getDefault()), created);
    }

    @GetMapping(value = "/{id}")
    public Rebel findById(@PathVariable("id") Long id) {
        return service.find(id);
    }
}
