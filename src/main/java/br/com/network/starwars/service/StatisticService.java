package br.com.network.starwars.service;

import br.com.network.starwars.dto.ItemStatisticDTO;
import br.com.network.starwars.dto.RebelStatisticDTO;
import br.com.network.starwars.dto.TraitorStatisticDTO;
import br.com.network.starwars.exception.ResourceNotFoundException;
import br.com.network.starwars.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticService {

    @Autowired
    private RebelRepository rebelRepository;

    @Autowired
    private MessageSource messages;

    public TraitorStatisticDTO countTraitors() {
        return rebelRepository.countTraitors();
    }

    public RebelStatisticDTO countRebels() {
        return rebelRepository.countRebels();
    }

    public List<ItemStatisticDTO> countItems() {
        return rebelRepository.countItems();
    }
}
