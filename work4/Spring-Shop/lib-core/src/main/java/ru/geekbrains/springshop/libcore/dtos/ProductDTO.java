package ru.geekbrains.springshop.libcore.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@NoArgsConstructor
@Data
public class ProductDTO {

    private Long id;
    private String title;
    private Float price;
}
