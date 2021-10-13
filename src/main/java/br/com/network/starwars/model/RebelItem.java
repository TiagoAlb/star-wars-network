package br.com.network.starwars.model;

import br.com.network.starwars.enumeration.ItemEnum;
import br.com.network.starwars.vo.RebelItemVO;
import br.com.network.starwars.vo.RebelVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class RebelItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemEnum item;

    private int quantity = 1;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private Rebel owner;

    public static RebelItem create(RebelItemVO obj) {
        return new ModelMapper().map(obj, RebelItem.class);
    }
}
