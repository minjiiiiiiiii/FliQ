package com.hongul.filq.ui.home

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.hongul.filq.R
import com.hongul.filq.model.Avatar
import com.hongul.filq.model.BusinessCard
import com.hongul.filq.ui.theme.PrimaryDeepDark

@Composable
fun EmptyBusinessCard() {
    BusinessCardLayout(
        businessCardView = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PrimaryDeepDark)
                    .clickable { },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "명함 추가",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                    ,
                    tint = Color.White
                )
                Text(
                    "나만의 브랜드를 담은\n나만의 명함을 직접 만들어보세요.",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    )
}

@Composable
fun BusinessCard(businessCard: BusinessCard) {
    val context = LocalContext.current

    BusinessCardLayout(
        businessCardView = {
            val imageFile = context.filesDir.resolve(businessCard.imagePath)
            when(imageFile.exists()) {
                true -> {
                    Image(
                        bitmap = BitmapFactory
                            .decodeFile(imageFile.absolutePath)
                            .asImageBitmap(),
                        contentDescription = "명함 이미지",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                else -> TODO("Placeholder 이미지 넣기")
            }
        },
        profileView = {
            businessCard.let {
                ProfileView(
                    name = it.name,
                    organization = it.organization,
                    department = it.department,
                    position = it.position,
                    avatar = it.avatar
                )
            }
        },
        introductionView = {

        }
    )
}

@Composable
private fun BusinessCardLayout(
    businessCardView: @Composable () -> Unit,
    profileView: @Composable () -> Unit = {},
    introductionView: @Composable () -> Unit = {},
) {
    ElevatedCard(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(9f / 5f)
            ) {
                businessCardView()
            }

            Text(
                "프로필",
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(9f / 5f)
            ) {
                profileView()
            }

            Text(
                "소개",
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )

            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 240.dp)
            ) {
                introductionView()
            }
        }
    }
}

@Composable
private fun ProfileView(
    name: String,
    organization: String,
    department: String,
    position: String,
    avatar: Avatar
) {
    val context = LocalContext.current
    val avatarPadding = PaddingValues(
        top = 40.dp,
        end = 80.dp
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    organization,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF696969)
                )
                Text(
                    "$department / $position",
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF969696)
                )
                Text(
                    name,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(avatarPadding)
                .zIndex(1f)
        ) {
            ProfileAvatar(
                context = context,
                avatar = avatar
            )
        }
    }
}

@Composable
private fun ProfileAvatar(context: Context, avatar: Avatar) {
    when {
        avatar.isDefault -> {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.avatar_default),
                contentDescription = "프로필 이미지",
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Fit
            )
        }
        avatar.isImage -> {
            context.filesDir.resolve(avatar.path!!).let {
                Image(
                    bitmap = when(it.exists()) {
                        true -> BitmapFactory
                            .decodeFile(it.absolutePath)
                            .asImageBitmap()
                        else -> ImageBitmap.imageResource(R.drawable.avatar_default)
                    },
                    contentDescription = "프로필 이미지",
                    modifier = Modifier.size(64.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        avatar.isSticker -> {
            avatar.sticker!!.let {
                Surface(
                    shape = CircleShape,
                    color = Color(it.color),
                    modifier = Modifier.size(64.dp)
                ) {
                    Image(
                        painter = BitmapPainter(
                            ImageBitmap.imageResource(R.drawable.stickers),
                            IntOffset(it.x, it.y),
                            IntSize(it.size, it.size)
                        ),
                        contentDescription = "스티커",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}