package com.resell.processor.processor.consumer;

import com.resell.processor.processor.service.GetNetPaymentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetNetAuthConsumer {

    private GetNetPaymentsService getNetPaymentsService;

//    @RabbitListener(queues = {"${api.rabbitmq.getnet.payments.credit.queue}"})
//    public void receiveCreatedAuthEvent(Long id) {
//        try {
//
//            System.out.println("**********************************************************");
//            System.out.println("**********************************************************");
//            System.out.println("**********************************************************");
//            System.out.println("RabbitListener +++++++++");
//            System.out.println("**********************************************************");
//
//            this.getNetPaymentsService.generateAccessToken();
//
//        } catch (Exception be) {
//            log.error(be.getMessage(), be);
//        }
//    }
}
