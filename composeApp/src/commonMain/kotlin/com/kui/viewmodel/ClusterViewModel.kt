package com.kui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kui.data.model.ClusterInfo
import com.kui.data.repository.KubernetesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClusterViewModel(
    private val repository: KubernetesRepository
) : ViewModel() {
    
    private val _clusterInfo = MutableStateFlow(ClusterInfo())
    val clusterInfo: StateFlow<ClusterInfo> = _clusterInfo.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    fun getClusterInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            _clusterInfo.value = repository.getClusterInfo()
            _isLoading.value = false
        }
    }
}