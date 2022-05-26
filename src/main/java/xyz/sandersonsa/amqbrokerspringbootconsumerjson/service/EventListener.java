package xyz.sandersonsa.amqbrokerspringbootconsumerjson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
        Pessoa pessoa = convertPessoa(message);
        log.info(" ## Pessoa :: {}", pessoa);
    }

    //Converter Message para Objeto
    private Pessoa convertPessoa(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        
        //JSON string to Java Object
        Pessoa pessoa = null;
        try {
            pessoa = objectMapper.readValue(message, Pessoa.class);            
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }        
        return pessoa;
    }
}