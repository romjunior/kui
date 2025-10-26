package com.kui.data.model

data class ClusterInfo(
    val serverUrl: String = "",
    val version: String = "",
    val isConnected: Boolean = false,
    val error: String? = null
)