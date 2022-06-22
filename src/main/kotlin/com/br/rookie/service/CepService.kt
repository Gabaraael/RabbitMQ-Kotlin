package com.br.rookie.service

import com.br.rookie.entity.CepEntity
import com.br.rookie.repository.CepRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.servlet.function.ServerResponse
import reactor.core.publisher.Mono
import java.util.function.Consumer


@Service
class CepService(@Autowired private var cepRepository:CepRepository, @Autowired private var webClient: WebClient) {

    fun findByCep(cep:String){
        var url: String = "https://viacep.com.br/ws/${cep}/json/"
        var cepMono: Mono<CepEntity> = this.webClient
            .method(HttpMethod.GET).uri(url)
            .retrieve()
            .bodyToMono(CepEntity::class.java)

         println(cepMono.block().toString())












    }

}