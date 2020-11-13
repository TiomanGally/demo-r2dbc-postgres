package de.gally.demoforr2dbc.port

import de.gally.demoforr2dbc.domain.CacheRepositoryService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class CacheGateway {

    @Bean
    fun routerDsl(
            cacheRepositoryService: CacheRepositoryService<ServerRequest, ServerResponse>
    ) = router {
        accept(MediaType.APPLICATION_JSON).nest {
            "/cache".nest {
                GET("/", cacheRepositoryService::findAll)
                PATCH("/", cacheRepositoryService::save)
            }
        }
    }
}
