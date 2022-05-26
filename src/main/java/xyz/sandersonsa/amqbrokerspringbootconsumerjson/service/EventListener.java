package xyz.sandersonsa.amqbrokerspringbootconsumerjson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        convertPessoa(message);
    }

    //Converter Message para Objeto
    private Pessoa convertPessoa(String message){
        ObjectMapper mapper = new ObjectMapper();

        //JSON string to Java Object
        Pessoa pessoa;
        try {
            pessoa = mapper.readValue(message, Pessoa.class);
            log.info(" ## Pessoa :: {}", pessoa);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}