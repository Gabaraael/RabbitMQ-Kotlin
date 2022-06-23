package com.br.rookie.repository

import com.br.rookie.entity.CepEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional


@Repository
interface CepRepository : JpaRepository<CepEntity, Long?> {


        fun findByCep(cep: String ): Optional<CepEntity>
}
