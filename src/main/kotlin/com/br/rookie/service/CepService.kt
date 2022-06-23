package com.br.rookie.service

import com.br.rookie.dtos.CepDTO
import com.br.rookie.entity.CepEntity

import com.br.rookie.repository.CepRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

import java.util.Optional


@Service
class CepService(@Autowired private val cepRepository:CepRepository, @Autowired private val webClient: WebClient) {



    fun findByCep(cep: String): CepDTO? {
        println("Entrei na Service")
        var cepOptional :Optional<CepEntity> = cepRepository.findByCep(cep)
        if (cepOptional.isPresent){
            return CepDTO(cepOptional.get().cep,cepOptional.get().logradouro, cepOptional.get().complemento, cepOptional.get().localidade, cepOptional.get().uf, cepOptional.get().ibge, cepOptional.get().gia, cepOptional.get().ddd, cepOptional.get().siafi)
        }


            var url: String = "https://viacep.com.br/ws/${cep}/json/"

            var cepMono: Mono<CepDTO> = webClient
                .method(HttpMethod.GET).uri(url)
                .retrieve()
                .bodyToMono(CepDTO::class.java)

            var cepDTO: CepDTO? = cepMono.block()
            lateinit var cepEntity: CepEntity
            return cepDTO
        }
    }
