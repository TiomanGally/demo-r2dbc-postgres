package de.gally.demoforr2dbc.adapter

import de.gally.demoforr2dbc.domain.Cache
import de.gally.demoforr2dbc.domain.CacheDto
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.LocalDateTime

@Service
class PostgresCacheRepository(
        private val databaseClient: DatabaseClient,
) {
    /** SQL Queries for querying the database */
    companion object {
        const val FIND_ALL = "select * from cache"
    }

    /** Finds all [Cache] from database */
    fun findAll(): Flux<CacheDto> {
        return databaseClient
                .sql(FIND_ALL)
                .map { row, _ ->
                    CacheDto(
                            value = row.get("value", String::class.java),
                            date = row.get("date", LocalDateTime::class.java),
                            system = row.get("system", String::class.java),
                    )
                }.all()
    }
}
