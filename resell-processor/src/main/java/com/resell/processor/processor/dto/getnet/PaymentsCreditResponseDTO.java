package com.resell.processor.processor.dto.getnet;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsCreditResponseDTO implements Serializable {

    private String payment_id;
    private String seller_id;
    private Long amount;
    private String currency;
    private String order_id;
    private String status;
    private String received_at;
    private CreditResponseDTO credit;

}
