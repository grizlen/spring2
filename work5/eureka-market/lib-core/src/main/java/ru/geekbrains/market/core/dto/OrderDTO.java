package ru.geekbrains.market.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class OrderDTO {

    private Long id;
    private Long product_id;
    private String product_title;
    private Long count;

}
