package de.gally.demoforr2dbc.adapter

import de.gally.demoforr2dbc.domain.CacheRepositoryService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class PublisherCacheRepositoryService(
        private val cacheRepository: CacheRepository
) : CacheRepositoryService {

    override fun findAll(serverRequest: ServerRequest): Mono<ServerResponse> =
            ServerResponse.ok().body(cacheRepository.findAll())
}
