package com.br.rookie.connections

import jakarta.annotation.PostConstruct
import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.stereotype.Component


@Component
class RabbitMQConnection(amqpAdmin: AmqpAdmin) {

    private var amqpAdmin :AmqpAdmin = amqpAdmin

    companion object{
        private const val NOME_EXCHANGE: String = "amq.direct"
    }

    private fun fila(nomeFila : String): Queue{

        return Queue(nomeFila, true, false, false);
    }

    private fun trocaDireta(): DirectExchange{
        return DirectExchange(NOME_EXCHANGE)
    }

    private fun relacionamento(fila: Queue, troca : DirectExchange): Binding{
     return Binding(fila.name, Binding.DestinationType.QUEUE, troca.name, fila.name, null)
    }

    @PostConstruct
    private fun adiciona(){

        var filaCep: Queue = this.fila("CEP")
        var troca : DirectExchange = this.trocaDireta()
        var ligacao : Binding = this.relacionamento(filaCep, troca)

        this.amqpAdmin.declareQueue(filaCep)
        this.amqpAdmin.declareExchange(troca)
        this.amqpAdmin.declareBinding(ligacao)


    }





}