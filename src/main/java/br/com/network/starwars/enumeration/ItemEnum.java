package br.com.network.starwars.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemEnum {
    GUN(4),
    MUNITION(3),
    WATER(2),
    FOOD(1);

    private int points;
}