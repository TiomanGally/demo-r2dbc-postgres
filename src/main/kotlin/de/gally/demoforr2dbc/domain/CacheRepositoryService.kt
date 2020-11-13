package de.gally.demoforr2dbc.domain

import reactor.core.publisher.Mono

interface CacheRepositoryService<IN, OUT> {

    fun findAll(serverRequest: IN): Mono<OUT>

    fun save(serverRequest: IN): Mono<OUT>
}
