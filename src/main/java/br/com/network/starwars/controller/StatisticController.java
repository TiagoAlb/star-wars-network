package br.com.network.starwars.controller;

import br.com.network.starwars.dto.ItemStatisticDTO;
import br.com.network.starwars.dto.NegotiationDTO;
import br.com.network.starwars.dto.RebelStatisticDTO;
import br.com.network.starwars.dto.TraitorStatisticDTO;
import br.com.network.starwars.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private StatisticService service;

    @GetMapping(value = "/traitors")
    public ResponseEntity<TraitorStatisticDTO> getTraitors() {
        return new ResponseEntity<>(service.countTraitors(), HttpStatus.OK);
    }

    @GetMapping(value = "/rebels")
    public ResponseEntity<RebelStatisticDTO> getRebels() {
        return new ResponseEntity<>(service.countRebels(), HttpStatus.OK);
    }

    @GetMapping(value = "/items")
    public ResponseEntity<List<ItemStatisticDTO>> getItems() {
        return new ResponseEntity<>(service.countItems(), HttpStatus.OK);
    }
}
