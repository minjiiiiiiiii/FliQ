package com.hongul.filq.model

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
)
