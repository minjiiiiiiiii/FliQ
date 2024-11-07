package com.hongul.filq.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hongul.filq.R
import com.hongul.filq.model.BusinessCard
import com.hongul.filq.ui.theme.PrimaryDeepDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val title = stringResource(R.string.app_name)

    val cards = remember { mutableStateListOf<BusinessCard>() }
    val pagerState = rememberPagerState(initialPage = 0) { cards.size+1 }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            if (pagerState.currentPage+1 < pagerState.pageCount) {
                ExtendedFloatingActionButton(
                    containerColor = Color.White,
                    contentColor = PrimaryDeepDark,
                    text = {
                        Text(
                            text = "전달 하기",
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    icon = {
                        Icon(
                            Icons.AutoMirrored.Filled.Send,
                            contentDescription = "명함 전달"
                        )
                    },
                    modifier = Modifier.scale(0.8f),
                    onClick = { }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PagerOffsets(pagerState.pageCount, pagerState.currentPage)
            HorizontalPager(pagerState) {
                EmptyBusinessCard()
            }
        }
    }
}

@Composable
fun PagerOffsets(pageCount: Int, currentPage: Int) {
    Row {
        for (i in 0 until pageCount) {
            Text(
                text = "●",
                fontSize = 8.sp,
                color = if (currentPage == i) Color.Gray else Color.LightGray,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}