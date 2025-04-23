package com.hongul.fliq.model.card

import androidx.compose.runtime.Immutable
import com.hongul.fliq.data.card.CardEntity

@Immutable
data class Card(
    val id: Int,
    val owner: Int,
    val name: String = "",
    val email: String = "",
    val contact: String = "",
    val profileImageURL: String,
    val cardImageURL: String,
    val organization: String,
    val position: String,
    val private: Boolean
) {
    companion object {
        fun fromEntity(entity: CardEntity) = Card(
            id = entity.id,
            owner = entity.owner,
            name = entity.name,
            email = entity.email,
            contact = entity.contact,
            profileImageURL = entity.profileImageURL,
            cardImageURL = entity.cardImageURL,
            organization = entity.organization,
            position = entity.position,
            private = entity.private
        )
    }
}

fun Card.toEntity() = CardEntity(
    id = id,
    owner = owner,
    name = name,
    email = email,
    contact = contact,
    profileImageURL = profileImageURL,
    cardImageURL = cardImageURL,
    organization = organization,
    position = position,
    private = private
)
