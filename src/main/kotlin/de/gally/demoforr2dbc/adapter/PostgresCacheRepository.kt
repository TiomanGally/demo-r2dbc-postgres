package de.gally.demoforr2dbc.adapter

import de.gally.demoforr2dbc.domain.Cache
import de.gally.demoforr2dbc.domain.CacheDto
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.bind
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class PostgresCacheRepository(
        private val databaseClient: DatabaseClient,
) {
    /** SQL Queries for querying the database */
    companion object {
        const val FIND_ALL = "select * from cache"
        const val SAVE = "insert into cache values (nextval('cache_id_seq'), :value, now(), :system)"
    }

    /** Finds all [Cache] from database */
    fun findAll(): Flux<CacheDto> {
        return databaseClient
                .sql(FIND_ALL)
                .toCacheDto()
                .all()
    }

    fun save(cacheDto: CacheDto): Mono<Void> {
        return databaseClient
                .sql(SAVE)
                .bind("value", cacheDto.value)
                .bind("system", cacheDto.value)
                .then()
    }

    fun DatabaseClient.GenericExecuteSpec.toCacheDto() =
            this.map { row ->
                println(row)
                CacheDto(
                        value = row.get("value", String::class.java),
                        system = row.get("system", String::class.java),
                        date = row.get("date", LocalDateTime::class.java),
                )
            }
}
