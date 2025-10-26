package com.kui.data.repository

import com.kui.data.model.ConfigPath

interface IConfigRepository {
    suspend fun createConfigDirectory(): ConfigPath
    suspend fun getConfigPath(): ConfigPath
}