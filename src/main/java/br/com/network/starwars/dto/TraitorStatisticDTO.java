package br.com.network.starwars.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TraitorStatisticDTO {

    public TraitorStatisticDTO(Long quantity, Long percentage) {
        this.quantity = quantity!=null?quantity:0L;
        this.percentage = percentage!=null?percentage:0L;
    }

    private Long quantity;
    private Long percentage;
}
