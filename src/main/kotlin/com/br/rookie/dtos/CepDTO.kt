package com.br.rookie.dtos

import com.fasterxml.jackson.annotation.JsonCreator

data class CepDTO(
    var cep: String? = null,
    var logradouro: String? = null,
    var complemento: String? = null,
    var localidade: String? = null,
    var uf: String? = null,
    var ibge: String? = null,
    var gia: String? = null,
    var ddd: String? = null,
    var siafi: String? = null
): java.io.Serializable
{





}










