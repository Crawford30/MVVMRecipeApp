package com.example.mvvmrecipeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Exposes the App by generating the app's top components
 * Its done by Hilt
 */
@HiltAndroidApp
class BaseApplication : Application(){}