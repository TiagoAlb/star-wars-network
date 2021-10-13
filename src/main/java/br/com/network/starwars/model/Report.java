package br.com.network.starwars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Report implements Serializable {

    public Report(Rebel x9, Rebel traitor) {
        this.x9 = x9;
        this.traitor = traitor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "x9Id", referencedColumnName = "id", nullable = false)
    private Rebel x9;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "traitorId", referencedColumnName = "id", nullable = false)
    private Rebel traitor;
}
