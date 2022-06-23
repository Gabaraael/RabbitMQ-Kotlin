package com.br.rookie.controller


import com.br.rookie.service.CepService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class CepController (@Autowired private var cepService: CepService){


   @GetMapping(path = ["/cep"])
   @Cacheable("cep")
    fun findByCep(@RequestParam cep: String): ResponseEntity<Any> {
        return try {
            println("Entrei na controller")
            ResponseEntity.ok().body(cepService.findByCep(cep))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }







}