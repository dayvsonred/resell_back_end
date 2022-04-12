package com.resell.processor.processor.dto.getnet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsCreditDTO implements Serializable {

    @JsonProperty("seller_id")
    private String sellerId;


//     "payment_id": "a5ec3320-69f3-4d48-8083-a1136ae3837f",
//             "seller_id": "f8555454-65a5-4ccc-85d2-f6a43f8c213e",
//             "amount": 1211,
//             "currency": "BRL",
//             "order_id": "123458",
//             "status": "APPROVED",
//             "received_at": "2022-04-12T01:32:46.058Z",
//             "credit": {
//        "delayed": false,
//                "authorization_code": "851984143659",
//                "authorized_at": "2022-04-12T01:32:46.058Z",
//                "reason_code": "0",
//                "reason_message": "transaction approved",
//                "acquirer": "GETNET",
//                "soft_descriptor": "EC*SANDBOX",
//                "terminal_nsu": "936840",
//                "acquirer_transaction_id": "49409753",
//                "transaction_id": "3241577400040796",
//                "first_installment_amount": "1211",
//                "other_installment_amount": "1211",
//                "total_installment_amount": "1211"
//    }

}
