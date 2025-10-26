package com.kui.data.repository

import com.kui.data.model.ClusterInfo
import io.kubernetes.client.openapi.ApiClient
import io.kubernetes.client.openapi.apis.CoreV1Api
import io.kubernetes.client.openapi.apis.VersionApi
import io.kubernetes.client.util.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class KubernetesRepositoryImpl : KubernetesRepository {
    
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    override suspend fun getClusterInfo(): ClusterInfo = withContext(Dispatchers.IO) {
        logger.info("Iniciando recuperação de informações do cluster")
        try {
            val client: ApiClient = Config.defaultClient()
            logger.debug("Cliente Kubernetes configurado: ${client.basePath}")
            
            val versionApi = VersionApi(client)
            val coreApi = CoreV1Api(client)
            
            val version = versionApi.getCode()
            logger.info("Versão do cluster obtida: ${version.major}.${version.minor}")
            
            val clusterInfo = ClusterInfo(
                serverUrl = client.basePath,
                version = "${version.major}.${version.minor}",
                isConnected = true
            )
            
            logger.info("Informações do cluster recuperadas com sucesso - URL: ${clusterInfo.serverUrl}")
            clusterInfo
        } catch (e: Exception) {
            logger.error("Erro ao recuperar informações do cluster: ${e.message}", e)
            ClusterInfo(
                error = e.message ?: "Erro desconhecido ao conectar com o cluster"
            )
        }
    }
}