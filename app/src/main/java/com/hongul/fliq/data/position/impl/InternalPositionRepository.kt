package com.hongul.fliq.data.position.impl

import com.hongul.fliq.data.position.PositionDao
import com.hongul.fliq.data.position.PositionRepository

class InternalPositionRepository(
    private val dao: PositionDao
): PositionRepository {
}