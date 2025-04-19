package com.hongul.fliq.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hongul.fliq.R
import com.hongul.fliq.ui.home.components.AppBar
import com.hongul.fliq.ui.home.styles.CardShareStyles
import com.hongul.fliq.ui.home.styles.CardShareStyles.Modifiers.cardImage
import com.hongul.fliq.ui.home.styles.CardShareStyles.Modifiers.cardImageContainer
import com.hongul.fliq.ui.home.styles.CardShareStyles.Modifiers.container
import com.hongul.fliq.ui.home.styles.CardShareStyles.Modifiers.content
import com.hongul.fliq.ui.home.styles.CardShareStyles.Modifiers.shareGuideImageContainer
import com.hongul.fliq.ui.home.styles.CardShareStyles.Modifiers.shareGuideImage
import com.hongul.fliq.ui.home.styles.CardShareStyles.Modifiers.root
import com.hongul.fliq.ui.home.styles.CardShareStyles.Modifiers.shareContainer
import com.hongul.fliq.ui.home.styles.CardShareStyles.Modifiers.shareContent

@Composable
fun CardShareScreen(
    navigator: NavHostController,
    shareViewModel: ShareViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.root(),
        containerColor = CardShareStyles.Colors.rootBackground,
        topBar = {
            AppBar(
                title = "FliQ",
                expand = false,
                actions = mapOf(
                    Icons.Outlined.Refresh to {}
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.container(padding)
        ) {
            Column(
                modifier = Modifier.content(),
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier.shareContainer(),
                    shape = RoundedCornerShape(32.dp),
                    color = CardShareStyles.Colors.surfaceBackground
                ) {
                    Column(
                        modifier = Modifier.shareContent(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val loaded = false
                        Box(
                            modifier = Modifier.shareGuideImageContainer(this)
                        ) {
                            if (loaded) {
                                Image(
                                    painter = painterResource(R.drawable.img_share),
                                    contentDescription = null,
                                    modifier = Modifier.shareGuideImage(this)
                                )
                            } else {
                                CircularProgressIndicator(
                                    modifier = Modifier.shareGuideImage(this)
                                )
                            }
                        }

                        ElevatedCard(
                            modifier = Modifier.cardImageContainer(this),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.img_card_example),
                                contentDescription = null,
                                modifier = Modifier.cardImage(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}
