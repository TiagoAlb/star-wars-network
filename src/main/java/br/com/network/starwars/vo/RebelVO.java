package br.com.network.starwars.vo;

import br.com.network.starwars.enumeration.GenderEnum;
import br.com.network.starwars.model.Rebel;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder({"id", "name", "age", "gender", "location", "items"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RebelVO extends RepresentationModel<RebelVO> implements Serializable {

    private Long id;
    private String name;
    private int age;
    private GenderEnum gender;
    private LocationVO location;
    private List<RebelItemVO> inventory;

    public static RebelVO create(Rebel obj) {
        return new ModelMapper().map(obj, RebelVO.class);
    }
}
