package com.hongul.fliq.data.card.impl

import com.hongul.fliq.data.card.CardDao
import com.hongul.fliq.data.card.CardRepository

class InternalCardRepository(
    private val dao: CardDao
): CardRepository {
}