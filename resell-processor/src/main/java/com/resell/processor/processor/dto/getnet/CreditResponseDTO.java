package com.resell.processor.processor.dto.getnet;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditResponseDTO implements Serializable {

    private Boolean delayed;
    private String authorization_code;
    private String authorized_at;
    private Long reason_code;
    private String reason_message;
    private String acquirer;
    private String soft_descriptor;
    private Long terminal_nsu;
    private Long acquirer_transaction_id;
    private Long transaction_id;
    private Long first_installment_amount;
    private Long other_installment_amount;
    private Long total_installment_amount;

}
