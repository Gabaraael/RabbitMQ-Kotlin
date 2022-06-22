package com.br.rookie.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import lombok.Data
import lombok.Getter
import lombok.Setter
import lombok.ToString

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "CEP")

class CepEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    var cep: String? = null
    var logradouro: String? = null
    var complemento: String? = null
    var bairro: String? = null
    var localidade: String? = null
    var uf: String? = null
    var ibge: String? = null
    var gia: String? = null
    var ddd: String? = null
    var siafi: String? = null
}