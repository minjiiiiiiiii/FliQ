package com.hongul.fliq

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class FliQApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
    }
}