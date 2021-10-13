package br.com.network.starwars.vo;

import br.com.network.starwars.model.Location;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"lat", "lng", "galaxyName"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LocationVO implements Serializable {

    private double lat;
    private double lng;
    private String galaxyName;

    public static LocationVO create(Location obj) {
        return new ModelMapper().map(obj, LocationVO.class);
    }
}
