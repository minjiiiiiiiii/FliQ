package com.hongul.filq.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hongul.filq.R

// 명함 정보를 관리하는 ViewModel 클래스
class ModifyBusinessCardInfo : ViewModel() {
    // 변경 시 UI가 자동으로 업데이트
    val cardItems = mutableStateOf(
        listOf(
            R.drawable.ic_person to "홍알홍알",
            R.drawable.ic_group to "캡스톤(1)",
            R.drawable.ic_star to "홍얼홍얼",
            R.drawable.ic_mic to "010 0000 0000",
            R.drawable.ic_pencil to "비지니스명함",
            R.drawable.ic_pencil to "xxx@stu.kmu.ac.kr",
            R.drawable.ic_link to "@hongeol.hongeol",
            R.drawable.ic_link to "hong",
            R.drawable.ic_link to "//fliq",
            R.drawable.ic_link to "http://digital.profile",
            R.drawable.ic_link to "eofk-dfdr"
        )
    )

    fun updateItem(index: Int, newText: String) {
        cardItems.value = cardItems.value.toMutableList().apply {
            this[index] = this[index].first to newText //아이콘은 그대로 유지하
        }
    }
}