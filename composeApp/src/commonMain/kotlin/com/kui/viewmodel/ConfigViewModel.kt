package com.kui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kui.data.model.ConfigPath
import com.kui.data.repository.IConfigRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory

class ConfigViewModel(
    private val configRepository: IConfigRepository
) : ViewModel() {
    
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val _configPath = MutableStateFlow<ConfigPath?>(null)
    val configPath: StateFlow<ConfigPath?> = _configPath.asStateFlow()
    
    init {
        initializeConfig()
    }
    
    private fun initializeConfig() {
        viewModelScope.launch {
            logger.info("Inicializando configuração do KUI")
            _configPath.value = configRepository.createConfigDirectory()
            logger.info("Configuração inicializada: ${_configPath.value?.path}")
        }
    }
    
    fun getConfigPath() {
        viewModelScope.launch {
            logger.debug("Obtendo caminho de configuração")
            _configPath.value = configRepository.getConfigPath()
        }
    }
}