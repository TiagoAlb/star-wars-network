package br.com.network.starwars.dto;

import br.com.network.starwars.enumeration.ItemEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemStatisticDTO {

    private ItemEnum item;
    private Long total;
    private Long quantityPerRebel;
    private Long quantityPerTraitor;
}
