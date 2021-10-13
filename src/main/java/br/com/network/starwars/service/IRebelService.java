package br.com.network.starwars.service;

import br.com.network.starwars.dto.NegotiationDTO;
import br.com.network.starwars.dto.ReportDTO;
import br.com.network.starwars.model.Rebel;
import br.com.network.starwars.model.Report;
import br.com.network.starwars.vo.LocationVO;
import br.com.network.starwars.vo.RebelVO;

public interface IRebelService {
    RebelVO save(RebelVO obj);
    Rebel find(Long id);
    LocationVO saveLocation(Long id, LocationVO obj);
    Report saveReport(Long id, ReportDTO obj) throws Exception;
    String negociateItems(Long id, NegotiationDTO obj) throws Exception;
}
