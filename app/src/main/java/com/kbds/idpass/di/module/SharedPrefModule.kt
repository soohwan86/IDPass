package com.kbds.idpass.di.module

import android.content.Context
import android.content.SharedPreferences
import com.kbds.idpass.KbIdPassApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SharedPrefModule {

    @Provides
    fun providesSharedPref(@ApplicationContext context: Context): SharedPreferences {
        val packageName = context.packageManager.getPackageInfo(context.packageName, 0)
        return context.getSharedPreferences(packageName.packageName, Context.MODE_PRIVATE)
    }
}