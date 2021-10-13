package br.com.network.starwars.dto;

import br.com.network.starwars.vo.RebelItemVO;
import br.com.network.starwars.vo.RebelVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NegotiationDTO {

    public NegotiationDTO(List<RebelItemVO> purchaseItems, List<RebelItemVO> paymentItems) {
        this.purchaseItems = purchaseItems;
        this.paymentItems = paymentItems;
    }

    private Long sellerId;

    private List<RebelItemVO> purchaseItems;

    private List<RebelItemVO> paymentItems;
}
