package br.com.network.starwars.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    MALE("MASCULINO"),
    FEMALE("FEMININO"),
    TRANSGENDER("TRANS"),
    OTHER("OUTRO");

    private String description;
}