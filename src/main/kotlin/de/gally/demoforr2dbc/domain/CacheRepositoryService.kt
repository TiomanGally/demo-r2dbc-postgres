package de.gally.demoforr2dbc.domain

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface CacheRepositoryService {

    fun findAll(serverRequest: ServerRequest): Mono<ServerResponse>
}
