package com.kui

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kui.platform.ConfigRepositoryImpl
import kotlinx.coroutines.runBlocking

fun main() {
    // Inicializa configuração antes da aplicação
    runBlocking {
        ConfigRepositoryImpl().createConfigDirectory()
    }
    
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "kui",
        ) {
            App()
        }
    }
}