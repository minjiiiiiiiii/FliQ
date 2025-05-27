package com.hongul.fliq.ui.customize

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hongul.fliq.ui.customize.page.BasicInformationPage
import com.hongul.fliq.ui.customize.page.BusinessCardCreationPage
import com.hongul.fliq.ui.customize.page.BusinessCardPhotoGuidePage
import com.hongul.fliq.ui.customize.page.BusinessCardPreviewPage
import com.hongul.fliq.ui.customize.page.BusinessCardScanPage
import com.hongul.fliq.ui.customize.page.CardInputOption
import com.hongul.fliq.ui.customize.page.OrganizationInfoPage
import com.hongul.fliq.ui.customize.page.ScannedInfoCheckPage
import com.hongul.fliq.ui.customize.page.SelectBusinessCardStylePage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCardGenerateScreen() {

    val ps = rememberPagerState(initialPage = 0) { 15 }//총 페이지 개수 바꾸기
    val scope = rememberCoroutineScope()
    var selectedOption by remember { mutableStateOf<CardInputOption?>(null) }

    var title by remember { mutableStateOf("") }
    var selectedTemplateImageRes by remember { mutableStateOf<Int?>(null) }
    var currentSNS by remember { mutableStateOf<String?>(null) } // 현재 선택된 SNS 이름


    LaunchedEffect(ps.currentPage, currentSNS) {
        title = when {
            else -> when (ps.currentPage) {
                0 -> "명함 생성"
                1 -> "명함 생성"
                2 -> "명함 생성"
                3 -> "명함 생성"
                4 -> "명함 생성"
                5 -> "명함 생성"
                6 -> "명함 생성"
                7 -> "명함 자동 생성"
                8 -> "명함 자동 생성"
                else -> "명함 생성"
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        when {
                            currentSNS != null -> currentSNS = null

                            else -> {
                                val navigator = null
                                when (ps.currentPage) {
                                    //0 -> navigator.popBackStack()
                                    1 -> scope.launch { ps.scrollToPage(0) }
                                    2 -> scope.launch { ps.scrollToPage(1) }
                                    3 -> scope.launch { ps.scrollToPage(2) }
                                    4 -> scope.launch { ps.scrollToPage(3) }
                                    5 -> scope.launch { ps.scrollToPage(0) }
                                    6 -> scope.launch { ps.scrollToPage(5) }
                                    7 -> scope.launch { ps.scrollToPage(6) }
                                    8 -> scope.launch { ps.scrollToPage(7) }
                                    else -> scope.launch { ps.scrollToPage(0) }
                                }
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "뒤로 가기"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (currentSNS != null) {

            } else {
                // 일반 페이지
                HorizontalPager(
                    state = ps,
                    modifier = Modifier
                        .fillMaxSize(),
                    userScrollEnabled = false
                ) { page ->
                    when (page) {
                        0 -> SelectBusinessCardStylePage(
                            onBack = { /* 예: navController.popBackStack() */ },
                            onPhotoSelected = { selectedOption = CardInputOption.Photo },
                            onAutoGenerateSelected = { selectedOption = CardInputOption.Auto },
                            onNavigateToNext = {
                                when (selectedOption) {
                                    CardInputOption.Photo -> scope.launch { ps.scrollToPage(1) } // 📸 사진 경로
                                    CardInputOption.Auto -> scope.launch { ps.scrollToPage(5) }  // 🤖 자동 생성 경로
                                    null -> {
                                        // 예: Toast로 "옵션을 선택하세요" 출력
                                    }
                                }
                            }
                        )

                        1 -> BusinessCardPhotoGuidePage(
                            onBack = { scope.launch { ps.scrollToPage(0) } },
                            onNext = { scope.launch { ps.scrollToPage(2) } },
                        )

                        2 -> BusinessCardScanPage(
                            onBack = { scope.launch { ps.scrollToPage(1) } },
                            onNext = { scope.launch { ps.scrollToPage(3) } },
                        )

                        3 -> ScannedInfoCheckPage(
                            onNext = { scope.launch { ps.scrollToPage(4) } },
                            onBack = { scope.launch { ps.scrollToPage(2) } },
                        )

                        4 -> BusinessCardPreviewPage(
                            //onNext = { scope.launch { ps.scrollToPage(5) } },
                            onBack = { scope.launch { ps.scrollToPage(3) } },
                        )

                        5 -> BasicInformationPage(
                            onNext = { scope.launch { ps.scrollToPage(6) } },
                            onBack = { scope.launch { ps.scrollToPage(0) } },
                        )

                        6 -> OrganizationInfoPage(
                            onNext = { scope.launch { ps.scrollToPage(7) } },
                            onBack = { scope.launch { ps.scrollToPage(5) } },
                        )

                        7 -> BusinessCardCreationPage(
                            onNext = { scope.launch { ps.scrollToPage(8) } },
                            onBack = { scope.launch { ps.scrollToPage(6) } },
                        )

                        8 -> BusinessCardPreviewPage(
                            onBack = { scope.launch { ps.scrollToPage(7) } },
                        )

                    }
                }
            }
        }
    }
}