package com.kui.platform

import com.kui.data.model.ConfigPath
import com.kui.data.repository.IConfigRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import java.io.File

class ConfigRepositoryImpl : IConfigRepository {
    
    private fun getConfigDirectoryPath(): String {
        val userHome = System.getProperty("user.home")
        val osName = System.getProperty("os.name").lowercase()
        
        return when {
            osName.contains("windows") -> "$userHome\\.kui"
            else -> "$userHome/.kui"
        }
    }
    
    override suspend fun createConfigDirectory(): ConfigPath = withContext(Dispatchers.IO) {
        val configPath = getConfigDirectoryPath()
        val configDir = File(configPath)
        
        if (!configDir.exists()) {
            configDir.mkdirs()
            LoggingService.configureLogging(configPath)
            LoggerFactory.getLogger(this::class.java).info("Pasta de configuração KUI criada: $configPath")
        }
        
        ConfigPath(configPath, configDir.exists())
    }
    
    override suspend fun getConfigPath(): ConfigPath = withContext(Dispatchers.IO) {
        val configPath = getConfigDirectoryPath()
        val configDir = File(configPath)
        
        ConfigPath(configPath, configDir.exists())
    }
}