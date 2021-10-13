package br.com.network.starwars.model;

import br.com.network.starwars.dto.NegotiationDTO;
import br.com.network.starwars.enumeration.GenderEnum;
import br.com.network.starwars.exception.ResourceNotFoundException;
import br.com.network.starwars.vo.RebelItemVO;
import br.com.network.starwars.vo.RebelVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Rebel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEnum gender;

    private boolean isTraitor;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ownerId")
    private List<RebelItem> inventory;

    @JsonIgnore
    @OneToMany(mappedBy = "traitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

    public void setTraitor() {
        if(reports.size() > 2) {
            isTraitor = true;
        }
    }

    public void negociatePurchase(NegotiationDTO negotiation) throws Exception {
        boolean ok = checkInventory(negotiation.getPaymentItems());

        if(!ok) {
            throw new Exception("Itens insuficientes para pagamento!");
        }

        addItems(negotiation.getPurchaseItems());
        removeItems(negotiation.getPaymentItems());
    }

    public void negociateSale(NegotiationDTO negotiation) throws Exception {
        boolean ok = checkInventory(negotiation.getPurchaseItems());

        if(!ok) {
            throw new Exception("Itens insuficientes para venda!");
        }

        addItems(negotiation.getPaymentItems());
        removeItems(negotiation.getPurchaseItems());
    }

    public void addItems(List<RebelItemVO> inventory) {
        inventory.stream().forEach(i ->
            this.inventory.stream().filter(o -> o.getItem().equals(i.getItem()))
                    .findAny()
                    .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + i.getQuantity()),
                        () -> this.inventory.add(RebelItem.create(i))
                    )
        );
    }

    public void removeItems(List<RebelItemVO> inventory) {
        inventory.stream().forEach(i ->
            this.inventory.stream()
                .filter(item -> item.getItem().equals(i.getItem()) && item.getQuantity() > i.getQuantity())
                .findFirst().ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() - i.getQuantity()),
                        () -> this.inventory.removeIf(item -> item.getItem().equals(i.getItem()))
                )
        );
    }

    public boolean checkInventory(List<RebelItemVO> inventory) {
        boolean ok = true;

        for(RebelItemVO i : inventory) {
            ok = this.inventory.stream().filter(
                            item -> item.getItem().equals(i.getItem()) && item.getQuantity() >= i.getQuantity())
                    .findAny().isPresent();

            if(!ok) {
                break;
            }
        }


        return ok;
    }

    public static void checkPoints(NegotiationDTO negotiation) throws Exception {
        Integer purchasePrice = negotiation.getPurchaseItems().stream().map(item -> item.getItem().getPoints() * item.getQuantity())
                .collect(Collectors.summingInt(Integer::intValue));

        Integer paymentPrice = negotiation.getPaymentItems().stream().map(item -> item.getItem().getPoints() * item.getQuantity())
                .collect(Collectors.summingInt(Integer::intValue));

        boolean equals = purchasePrice.equals(paymentPrice);

        if(!equals) {
            throw new Exception("Soma de pontos para compra e venda incompatíveis!");
        }
    }

    public static Rebel create(RebelVO obj) {
        return new ModelMapper().map(obj, Rebel.class);
    }
}
