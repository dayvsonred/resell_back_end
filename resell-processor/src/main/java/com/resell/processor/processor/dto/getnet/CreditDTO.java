package com.resell.processor.processor.dto.getnet;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditDTO implements Serializable {

    private Boolean delayed;
    private Boolean save_card_data;
    private String transaction_type;
    private Long number_installments;
    private CardDTO card;
}
