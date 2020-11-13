package de.gally.demoforr2dbc.adapter

import de.gally.demoforr2dbc.domain.CacheDto
import de.gally.demoforr2dbc.domain.CacheRepositoryService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class PublisherCacheRepositoryService(
        private val cacheRepository: PostgresCacheRepository
) : CacheRepositoryService<ServerRequest, ServerResponse> {

    override fun findAll(serverRequest: ServerRequest): Mono<ServerResponse> =
            ServerResponse.ok().body(cacheRepository.findAll())

    override fun save(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest
                .bodyToMono(String::class.java)
                .flatMap {
                    val system = serverRequest.headers().firstHeader("X-System")
                    val cacheDto = CacheDto(it, system)
                    val savedItem = cacheRepository.save(cacheDto)

                    ServerResponse.ok().body(savedItem)
                }
    }
}
