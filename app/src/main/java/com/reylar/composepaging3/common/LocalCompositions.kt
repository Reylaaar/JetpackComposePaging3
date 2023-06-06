package com.reylar.composepaging3.common

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.reylar.composepaging3.presentation.home.HomeViewModel


val localNavController =
    compositionLocalOf<NavHostController> {  error("No localNavController found!") }

val localNavHomeViewModel =
    compositionLocalOf<HomeViewModel> { error("No HomeViewModel found!") }