package com.br.rookie.service


import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class RabbitMQService(
    @Autowired private var rabbitTemplate : RabbitTemplate) {

    var objectMapper: ObjectMapper = ObjectMapper()

    fun sendMessage(nomeFila:String, mensagem : Any){
        this.rabbitTemplate.convertAndSend(nomeFila, this.objectMapper.writeValueAsString(mensagem));
    }
}