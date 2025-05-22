package com.hongul.fliq.ui.customize.page

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

import com.hongul.fliq.R
import java.io.IOException

fun saveImageToGallery(context: Context, bitmap: Bitmap) {
    try {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "${System.currentTimeMillis()}.jpg") // 파일 이름
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg") // MIME 타입
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/FliQ") // 저장 경로
        }

        val uri: Uri? = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            context.contentResolver.openOutputStream(it)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
            }
            MediaScannerConnection.scanFile(
                context,
                arrayOf(it.toString()),
                arrayOf("image/jpeg"),
                null
            )
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestGalleryPermission(onPermissionGranted: () -> Unit) {
    val permissionState = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)

    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        } else {
            onPermissionGranted()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerScreen(onNext: () -> Unit, onImageSelected: (Bitmap) -> Unit) {
    var selectedImage by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    // 권한 요청 처리
    val permissionState = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)

    // 권한 요청 상태를 확인하는 변수
    val isPermissionGranted = permissionState.status.isGranted

    // 권한이 허용되지 않으면 권한 요청
    LaunchedEffect(permissionState.status) {
        if (!isPermissionGranted) {
            permissionState.launchPermissionRequest()
        }
    }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                    selectedImage = bitmap
                    onImageSelected(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF5F5F5),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "사진첩에서 가져오기",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "뒤로 가기"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        selectedImage?.let { image ->
                            Image(
                                bitmap = image.asImageBitmap(),
                                contentDescription = "Selected Image",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        } ?: run {
                            Image(
                                painter = painterResource(id = R.drawable.ju_basic_business_card),
                                contentDescription = "Add Business Card",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(250.dp))

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
           }
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            imagePickerLauncher.launch("image/*")
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .padding(end = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text(text = "명함 가져오기", color = Color.Black, fontSize = 16.sp)
                    }

                    Button(
                        onClick = {
                            onNext()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .padding(start = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF125422))
                    ) {
                        Text(text = "확인", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
