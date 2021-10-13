package br.com.network.starwars.service;

import br.com.network.starwars.dto.NegotiationDTO;
import br.com.network.starwars.dto.ReportDTO;
import br.com.network.starwars.exception.ResourceNotFoundException;
import br.com.network.starwars.model.Location;
import br.com.network.starwars.model.Rebel;
import br.com.network.starwars.model.Report;
import br.com.network.starwars.repository.RebelRepository;
import br.com.network.starwars.repository.ReportRepository;
import br.com.network.starwars.vo.LocationVO;
import br.com.network.starwars.vo.RebelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RebelService implements IRebelService {

    @Autowired
    private RebelRepository rebelRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MessageSource messages;

    @Override
    public RebelVO save(RebelVO obj) {
        obj.setId(0L);
        return RebelVO.create(rebelRepository.save(Rebel.create(obj)));
    }

    @Override
    public Rebel find(Long id) {
        var entity = rebelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage("message.notFound.id", null, null)
                ));
        return entity;
    }

    @Override
    public LocationVO saveLocation(Long id, LocationVO obj) {
        var rebel = find(id);
        rebel.setLocation(Location.create(obj));

        return LocationVO.create(rebelRepository.save(rebel).getLocation());
    }

    @Override
    public Report saveReport(Long id, ReportDTO obj) throws Exception {
        var x9 = find(id);
        var traitor = find(obj.getTraitorId());
        boolean reported = traitor.getReports()
                .stream()
                .filter(r -> r.getX9().getId() == id)
                .collect(Collectors.toList()).size() > 0;

        if(reported) {
            throw new Exception(messages.getMessage("message.rebel.reported", null, null));
        }

        traitor.getReports().add(new Report(x9, traitor));
        traitor.setTraitor();
        traitor = rebelRepository.save(traitor);
        var reports = traitor.getReports();

        return reports.get(reports.size() - 1);
    }

    @Override
    public String negociateItems(Long id, NegotiationDTO obj) throws Exception {
        Rebel buyer = find(id);
        Rebel seller = find(obj.getSellerId());

        Rebel.checkPoints(obj);

        buyer.negociatePurchase(obj);
        seller.negociateSale(obj);

        rebelRepository.save(buyer);
        rebelRepository.save(seller);

        return messages.getMessage("message.rebel.negociation", null, null);
    }
}
