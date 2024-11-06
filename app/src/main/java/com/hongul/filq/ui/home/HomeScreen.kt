package com.hongul.filq.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hongul.filq.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    bottomNavigation: @Composable () -> Unit
) {
    val title = stringResource(R.string.app_name)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(title) }
            )
        },
        bottomBar = {
            bottomNavigation()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pagerState = rememberPagerState(initialPage = 0) { 2 }

            PagerOffsets(pagerState.pageCount, pagerState.currentPage)
            HorizontalPager(pagerState) { page ->
                if (page+1 == pagerState.pageCount) {

                }
            }
        }
    }
}

@Composable
fun PagerOffsets(pageCount: Int, currentPage: Int) {
    Row {
        for (i in 0 until pageCount) {
            Text(
                text = if (i == currentPage) "●" else "○",
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}