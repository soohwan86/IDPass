package com.kbds.idpass.di.module

import com.kbds.idpass.data.extension.encryptAes256
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object KBPassModule {

    @Provides
    fun provideKbPass(): String {
        val string = "모바일사원증"
        return string.encryptAes256()
    }
}