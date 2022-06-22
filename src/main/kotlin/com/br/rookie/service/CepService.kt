package com.br.rookie.service

import com.br.rookie.dtos.CepDTO

import com.br.rookie.repository.CepRepository

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

import reactor.core.publisher.Mono



@Service
class CepService(@Autowired private var cepRepository:CepRepository, @Autowired private var webClient: WebClient) {


    fun findByCep(cep:String): CepDTO? {
        var url: String = "https://viacep.com.br/ws/${cep}/json/"
        var cepMono: Mono<CepDTO> = webClient
            .method(HttpMethod.GET).uri(url)
            .retrieve()
            .bodyToMono(CepDTO::class.java)

        println("test")
        var cepDTO : CepDTO? = cepMono.block()
        return cepDTO
















    }

}