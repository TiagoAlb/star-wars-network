package br.com.network.starwars;


import br.com.network.starwars.dto.NegotiationDTO;
import br.com.network.starwars.enumeration.ItemEnum;
import br.com.network.starwars.model.Rebel;
import br.com.network.starwars.model.RebelItem;
import br.com.network.starwars.vo.RebelItemVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class RebelTests {

    @InjectMocks
    private Rebel buyer;

    @InjectMocks
    private Rebel seller;

    @Test
    public void exceptionWhenSellerItemsQuantityIsNotEnough() {

        ReflectionTestUtils.setField(seller, "inventory", getInventoryMock());

        Exception e = assertThrows(Exception.class, () -> {
            seller.negociateSale(getNotAcceptableNegotiationQuantityForSaleMock());
        });

        String expectedMessage = "Itens insuficientes para venda!";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void exceptionWhenBuyerItemsQuantityIsNotEnough() {

        ReflectionTestUtils.setField(buyer, "inventory", getInventoryMock());

        Exception e = assertThrows(Exception.class, () -> {
            buyer.negociatePurchase(getNotAcceptableNegotiationQuantityForPaymentMock());
        });

        String expectedMessage = "Itens insuficientes para pagamento!";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void exceptionWhenItemsPointsIsNotEnough() {

        Exception e = assertThrows(Exception.class, () -> {
            Rebel.checkPoints(getNotAcceptableNegotiationPointsMock());
        });

        String expectedMessage = "Soma de pontos para compra e venda incompatíveis!";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void successfulNegotiation() throws Exception {

        ReflectionTestUtils.setField(buyer, "inventory", getInventoryMock());
        ReflectionTestUtils.setField(seller, "inventory", getInventoryMock());

        buyer.negociatePurchase(getAcceptableNegotiationMock());
        seller.negociateSale(getAcceptableNegotiationMock());

        assertTrue(buyer.getInventory().stream()
                .filter(item -> item.getItem().equals(ItemEnum.GUN) && item.getQuantity() == 3)
                .findAny().isPresent());

        assertTrue(buyer.getInventory().stream()
                .filter(item -> item.getItem().equals(ItemEnum.FOOD) && item.getQuantity() == 6)
                .findAny().isPresent());

        assertTrue(buyer.getInventory().stream()
                .filter(item -> item.getItem().equals(ItemEnum.MUNITION) && item.getQuantity() == 28)
                .findAny().isPresent());

        assertTrue(seller.getInventory().stream()
                .filter(item -> item.getItem().equals(ItemEnum.GUN) && item.getQuantity() == 1)
                .findAny().isPresent());

        assertTrue(seller.getInventory().stream()
                .filter(item -> item.getItem().equals(ItemEnum.FOOD) && item.getQuantity() == 2)
                .findAny().isPresent());

        assertTrue(seller.getInventory().stream()
                .filter(item -> item.getItem().equals(ItemEnum.MUNITION) && item.getQuantity() == 32)
                .findAny().isPresent());
    }

    public List<RebelItem> getInventoryMock() {
        List<RebelItem> inventory = new ArrayList<>(
                Arrays.asList(
                        new RebelItem(1L, ItemEnum.GUN, 2, new Rebel()),
                        new RebelItem(1L, ItemEnum.MUNITION, 30, new Rebel()),
                        new RebelItem(1L, ItemEnum.WATER, 3, new Rebel()),
                        new RebelItem(1L, ItemEnum.FOOD, 4, new Rebel())
                )
        );

        return inventory;
    }

    public NegotiationDTO getAcceptableNegotiationMock() {
        List<RebelItemVO> purchaseItems = new ArrayList<>(
                Arrays.asList(
                        new RebelItemVO(ItemEnum.GUN, 1),
                        new RebelItemVO(ItemEnum.FOOD, 2)
                )
        );

        List<RebelItemVO> paymentItems = new ArrayList<>(
                Arrays.asList(
                        new RebelItemVO(ItemEnum.MUNITION, 2)
                )
        );

        return new NegotiationDTO(purchaseItems, paymentItems);
    }

    public NegotiationDTO getNotAcceptableNegotiationPointsMock() {
        List<RebelItemVO> purchaseItems = new ArrayList<>(
                Arrays.asList(
                        new RebelItemVO(ItemEnum.GUN, 1),
                        new RebelItemVO(ItemEnum.FOOD, 2)
                )
        );

        List<RebelItemVO> paymentItems = new ArrayList<>(
                Arrays.asList(
                        new RebelItemVO(ItemEnum.WATER, 1)
                )
        );

        return new NegotiationDTO(purchaseItems, paymentItems);
    }

    public NegotiationDTO getNotAcceptableNegotiationQuantityForSaleMock() {
        List<RebelItemVO> purchaseItems = new ArrayList<>(
                Arrays.asList(
                        new RebelItemVO(ItemEnum.GUN, 4)
                )
        );

        List<RebelItemVO> paymentItems = new ArrayList<>(
                Arrays.asList(
                        new RebelItemVO(ItemEnum.MUNITION, 4),
                        new RebelItemVO(ItemEnum.WATER, 2)
                )
        );

        return new NegotiationDTO(purchaseItems, paymentItems);
    }

    public NegotiationDTO getNotAcceptableNegotiationQuantityForPaymentMock() {
        List<RebelItemVO> purchaseItems = new ArrayList<>(
                Arrays.asList(
                        new RebelItemVO(ItemEnum.GUN, 2)
                )
        );

        List<RebelItemVO> paymentItems = new ArrayList<>(
                Arrays.asList(
                        new RebelItemVO(ItemEnum.FOOD, 8)
                )
        );

        return new NegotiationDTO(purchaseItems, paymentItems);
    }
}
