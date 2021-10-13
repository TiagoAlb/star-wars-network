package br.com.network.starwars.repository;

import br.com.network.starwars.dto.ItemStatisticDTO;
import br.com.network.starwars.dto.RebelStatisticDTO;
import br.com.network.starwars.dto.TraitorStatisticDTO;
import br.com.network.starwars.model.Rebel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RebelRepository extends PagingAndSortingRepository<Rebel, Long> {

    @Query("SELECT new br.com.network.starwars.dto.TraitorStatisticDTO("
            + "SUM(CASE WHEN (rebel.isTraitor = true) THEN 1 ELSE 0 END), "
            + "SUM(CASE WHEN (rebel.isTraitor = true) THEN 1 ELSE 0 END) * 100 / SUM(rebel)) "
            + "FROM Rebel rebel")
    TraitorStatisticDTO countTraitors();

    @Query("SELECT new br.com.network.starwars.dto.RebelStatisticDTO("
            + "SUM(CASE WHEN (rebel.isTraitor = false) THEN 1 ELSE 0 END), "
            + "SUM(CASE WHEN (rebel.isTraitor = false) THEN 1 ELSE 0 END) * 100 / SUM(rebel)) "
            + "FROM Rebel rebel")
    RebelStatisticDTO countRebels();

    @Query("SELECT new br.com.network.starwars.dto.ItemStatisticDTO("
            + "item.item, "
            + "SUM(item.quantity), "
            + "CASE WHEN "
            + "(SUM(CASE WHEN (rebel.isTraitor = false) THEN item.quantity ELSE 0 END) > 0) "
            + "THEN (SUM(CASE WHEN (rebel.isTraitor = false) THEN item.quantity ELSE 0 END) / SUM(CASE WHEN (rebel.isTraitor = false) THEN 1 ELSE 0 END)) ELSE 0 END, "
            + "CASE WHEN "
            + "(SUM(CASE WHEN (rebel.isTraitor = true) THEN item.quantity ELSE 0 END) > 0) "
            + "THEN (SUM(CASE WHEN (rebel.isTraitor = true) THEN item.quantity ELSE 0 END) / SUM(CASE WHEN (rebel.isTraitor = true) THEN 1 ELSE 0 END)) ELSE 0 END) "
            + "FROM Rebel rebel JOIN rebel.inventory item GROUP BY item.item")
    List<ItemStatisticDTO> countItems();
}
