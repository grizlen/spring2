package ru.geekbrains.market.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProductDTO {

    private Long id;
    private String title;
    private Float price;

}
