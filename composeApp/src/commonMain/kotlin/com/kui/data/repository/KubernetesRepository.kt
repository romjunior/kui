package com.kui.data.repository

import com.kui.data.model.ClusterInfo

interface KubernetesRepository {
    suspend fun getClusterInfo(): ClusterInfo
}