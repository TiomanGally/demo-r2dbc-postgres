package de.gally.demoforr2dbc.adapter

import de.gally.demoforr2dbc.domain.Cache
import de.gally.demoforr2dbc.domain.CacheDto
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.LocalDateTime

@Service
class CacheRepository(
        private val databaseClient: DatabaseClient,
) {

    /** Finds all [Cache] from database */
    fun findAll(): Flux<CacheDto> {
        return databaseClient
                .sql(Cache.FIND_ALL)
                .map { row, _ ->
                    val value = row.get("value", String::class.java)
                    val system = row.get("system", String::class.java)
                    val date = row.get("date", LocalDateTime::class.java)
                    CacheDto(value, date, system)
                }.all()
    }
}
