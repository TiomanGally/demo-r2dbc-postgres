package de.gally.demoforr2dbc.domain

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Cache(
        val value: String,
        val date: LocalDateTime,
        val system: String,
        @Id val id: Long
)
