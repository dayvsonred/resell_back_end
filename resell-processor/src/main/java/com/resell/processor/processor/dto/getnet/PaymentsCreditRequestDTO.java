package com.resell.processor.processor.dto.getnet;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsCreditRequestDTO implements Serializable {

    private String seller_id;
    private String amount;
    private OrderDTO order;
    private CustomerDTO customer;
    private DeviceDTO device;
    private List<ShippingsDTO> shippings;
    private SubMerchantDTO sub_merchant;
    private CreditDTO credit;

}
