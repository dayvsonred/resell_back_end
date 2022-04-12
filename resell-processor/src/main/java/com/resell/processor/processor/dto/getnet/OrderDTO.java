package com.resell.processor.processor.dto.getnet;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO implements Serializable {

    private String order_id;
    private Long sales_tax;
    private String product_type;
}
