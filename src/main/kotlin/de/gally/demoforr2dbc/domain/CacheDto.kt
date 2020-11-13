package de.gally.demoforr2dbc.domain

import java.time.LocalDateTime

data class CacheDto(
        val value: String?,
        val date: LocalDateTime?,
        val system: String?,
)
