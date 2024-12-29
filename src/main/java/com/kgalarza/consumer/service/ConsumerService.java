package com.kgalarza.consumer.service;

import com.kgalarza.consumer.configuration.RabbitMQConfiguration;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {

//    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME)
//    public void receiveMessage(String message) {
//        System.out.println("Message received: " + message);
//    }

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, Message messageDetails) throws IOException {
        try {
            System.out.println("Message received: " + message);

            // Confirmar mensaje procesado
            channel.basicAck(messageDetails.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            // Si ocurre un error, reencola el mensaje
            channel.basicNack(messageDetails.getMessageProperties().getDeliveryTag(), false, true);
        }
        /*basicAck: Confirma que el mensaje fue procesado correctamente.
          basicNack: Indica un error en el procesamiento y puede reenviar el mensaje (requeue = true).*/
    }

}
