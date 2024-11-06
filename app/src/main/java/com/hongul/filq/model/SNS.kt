package com.hongul.filq.model

import androidx.annotation.DrawableRes
import com.hongul.filq.R

sealed class SNS(
    @DrawableRes val icon: Int,
    val link: String,
    val idParser: (String) -> String
) {
    val userId: String
        get() = idParser(link)

    class Facebook(link: String) : SNS(R.drawable.ic_facebook, link, DEFAULT_PARSER)
    class Instagram(link: String) : SNS(R.drawable.ic_instagram, link, DEFAULT_PARSER)

    companion object {
        val DEFAULT_PARSER: (String) -> String = { link -> link.split("/").last() }
    }
}