package br.com.network.starwars.model;

import br.com.network.starwars.vo.LocationVO;
import br.com.network.starwars.vo.RebelVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double lat;
    private double lng;
    private String galaxyName;

    public static Location create(LocationVO obj) {
        return new ModelMapper().map(obj, Location.class);
    }
}
