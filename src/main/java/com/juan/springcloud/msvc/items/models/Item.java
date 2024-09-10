package com.juan.springcloud.msvc.items.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Item {

    private Product product;
    private int quantity;

    public Double getTotal() {
        return product.getPrice() * quantity;
    }

}
