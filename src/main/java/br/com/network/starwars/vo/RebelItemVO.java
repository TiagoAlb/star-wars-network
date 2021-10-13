package br.com.network.starwars.vo;

import br.com.network.starwars.enumeration.ItemEnum;
import br.com.network.starwars.model.RebelItem;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@JsonPropertyOrder({"name", "points"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RebelItemVO implements Serializable {

    private ItemEnum item;
    private int quantity;

    public static RebelItemVO create(RebelItem obj) {
        return new ModelMapper().map(obj, RebelItemVO.class);
    }
}
