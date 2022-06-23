package com.br.rookie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient


@Configuration
@SpringBootApplication
class RookieApplication

@Configuration
@EnableCaching
class Configuration {

	@Bean
	fun cacheManager() :CacheManager{
		return ConcurrentMapCacheManager("cep")
	}

	@Bean
	fun webClient(builder: WebClient.Builder): WebClient {
		return builder
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build()
	}
		}

fun main(args: Array<String>) {
	runApplication<RookieApplication>(*args)
}
