package xyz.sandersonsa.amqbrokerspringbootconsumerjson.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import xyz.sandersonsa.amqbrokerspringbootconsumerjson.model.Pessoa;


@Component
@Slf4j
public class EventListener {

    @Value("${app.springboot.queue}")
    private String destinationQueue;

    @JmsListener(destination = "${app.springboot.queue}")
    public void receiveMessage(String message) {
        log.info(" ## Message :: {} ", message);
        // System.out.println("Mensagem da fila:" + ticket);
    }

    //Converter Message para Objeto
    private Pessoa convertPessoa(String message){
        return null;
    }
}