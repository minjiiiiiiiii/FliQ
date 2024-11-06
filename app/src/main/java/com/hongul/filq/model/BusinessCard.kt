package com.hongul.filq.model

data class BusinessCard(
    val name: String,
    val title: String,
    val phoneNumber: String,
    val email: String,
    val address: String,
    val group: String,
    val department: String,
    val position: String,
    val sns: List<SNS>,
)
