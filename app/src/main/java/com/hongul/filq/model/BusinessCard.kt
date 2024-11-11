package com.hongul.filq.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.hongul.filq.R

data class BusinessCard(
    // 명함 고유 id(userId@prefix(autoIncremented) 형식)
    // ex) 94842789402141@24
    val id: String,

    val name: String, // 본명
    val title: String, // 명함 타이틀
    val phoneNumber: String, // 전화번호
    val email: String, // 이메일
    val address: String, // 주소
    val organization: String, // 소속
    val department: String, // 부서
    val position: String, // 직책
    val sns: List<SNS>, // 소셜 계정 목록

    val imagePath: String, // 명함 이미지 경로
    val avatar: Avatar, // 명함 프로필 이미지
    val introduction: String, // 명함 소개

    val memo: String = "", // 명함 메모
)

sealed class SNS(
    @DrawableRes val icon: Int,
    val link: String,
    val idParser: (String) -> String
) {
    val userId: String
        get() = idParser(link)

    class Facebook(link: String) :  SNS(R.drawable.logo_facebook, link, DEFAULT_PARSER)
    class Instagram(link: String) : SNS(R.drawable.logo_instagram, link, DEFAULT_PARSER)
    class X(link: String) :         SNS(R.drawable.logo_x, link, DEFAULT_PARSER)
    class Youtube(link: String) :   SNS(R.drawable.logo_youtube, link, DEFAULT_PARSER)

    companion object {
        val DEFAULT_PARSER: (String) -> String = { link -> link.split("/").last() }
    }
}

data class Avatar(
    val path: String? = null,
    val sticker: Sticker? = null
) {
    val isImage: Boolean
        get() = path != null
    val isSticker: Boolean
        get() = sticker != null
    val isDefault: Boolean
        get() = !isImage && !isSticker
}

data class Sticker(
    val pos: Int,
    val color: Color
) {
    companion object {
        const val SIZE = 256
    }

    val size: Int get() = SIZE
    val x: Int get() = (pos % 4) * size
    val y: Int get() = (pos / 4) * size
}