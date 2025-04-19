package com.hongul.fliq.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hongul.fliq.R
import com.hongul.fliq.ui.home.components.AppBar
import com.hongul.fliq.ui.home.components.CardPageView
import com.hongul.fliq.ui.home.components.CreateCardPageView
import com.hongul.fliq.ui.home.components.InnerContentMode
import com.hongul.fliq.ui.home.styles.HomeStyles
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.cardPager
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.container
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.content
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.fabIcon
import com.hongul.fliq.ui.home.styles.HomeStyles.Modifiers.root
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigator: NavHostController,
    homeViewModel: HomeViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.root(),
        containerColor = HomeStyles.Colors.rootBackground,
        topBar = {
            AppBar(
                title = "FliQ",
                actions = mapOf(
                    Icons.Outlined.Add to {},
                    Icons.AutoMirrored.Outlined.List to {}
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = HomeStyles.Colors.fabContainer,
                contentColor = HomeStyles.Colors.fabContent,
                text = {
                    Text(
                        text = "내 근처 명함"
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_fab_home),
                        contentDescription = null,
                        modifier = Modifier.fabIcon(),
                        tint = HomeStyles.Colors.fabIcon
                    )
                },
                onClick = {}
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        Box(
            modifier = Modifier.container(padding)
        ) {
            Column(
                modifier = Modifier.content()
            ) {

                val cardList = listOf(1,2,3)
                val pagerState = rememberPagerState { cardList.size + 1 }

                var showInnerContent by remember { mutableStateOf(true) }

                LaunchedEffect(pagerState.currentPageOffsetFraction) {
                    showInnerContent = pagerState.currentPageOffsetFraction.absoluteValue <= 0.01f
                }

                HorizontalPager(
                    modifier = Modifier.cardPager(),
                    state = pagerState,
                    pageSpacing = 16.dp,
                    verticalAlignment = Alignment.Top
                ) { page ->
                    if(page == pagerState.pageCount-1) {
                        CreateCardPageView()
                    } else {
                        CardPageView(
                            onClickShare = {
                                navigator.navigate("share")
                            },
                            innerContentMode =
                                if (showInnerContent) InnerContentMode.SHOW
                                else InnerContentMode.PENDING
                        )
                    }
                }
            }
        }
    }
}