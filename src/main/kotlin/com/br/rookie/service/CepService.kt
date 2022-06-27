package com.br.rookie.service

import com.br.rookie.const.RabbitMQConst
import com.br.rookie.dtos.CepDTO
import com.br.rookie.entity.CepEntity
import com.br.rookie.repository.CepRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.JsonObject
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.util.*


@Service
class CepService(
    @Autowired private val cepRepository:CepRepository,
    @Autowired private val webClient: WebClient,
    @Autowired private var  rabbitMQService: RabbitMQService) {

    fun findByCep(cep: String): CepDTO? {


        var url: String = "https://viacep.com.br/ws/${cep}/json/"
        var cepOptional: Optional<CepEntity> = cepRepository.findByCep(cep)

        if (cepOptional.isPresent) {
            return CepDTO(
                cepOptional.get().cep,
                cepOptional.get().logradouro,
                cepOptional.get().complemento,
                cepOptional.get().localidade,
                cepOptional.get().uf,
                cepOptional.get().ibge,
                cepOptional.get().gia,
                cepOptional.get().ddd,
                cepOptional.get().siafi
            )
        }

        var cepDTO: CepDTO? = webClient
            .method(HttpMethod.GET).uri(url)
            .retrieve()
            .bodyToMono(CepDTO::class.java)
            .block()

        if (cepDTO != null) {
            rabbitMQService.sendMessage(RabbitMQConst.FILA_CEP, cepDTO)
            return cepDTO
        }

        return throw Exception("Erro: Inconsistência no serviço de busca CEP ")
    }

    @RabbitListener(queues = [RabbitMQConst.FILA_CEP])
    fun consumer(message: String){
        println(message)
        try {
            this.cepRepository.save(ObjectMapper().readValue(message, CepEntity::class.java))
        }catch (exception : Exception){
            println(exception)
            println("FAIL")
        }









}



}


