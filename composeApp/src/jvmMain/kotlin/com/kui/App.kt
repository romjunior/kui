package com.kui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kui.data.repository.KubernetesRepositoryImpl
import com.kui.viewmodel.ClusterViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val viewModel: ClusterViewModel = viewModel { ClusterViewModel(KubernetesRepositoryImpl()) }
    val clusterInfo by viewModel.clusterInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Kubernetes UI",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Button(
                onClick = { viewModel.getClusterInfo() },
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text("Click me!")
            }
            
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Cluster Info",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    if (clusterInfo.error != null) {
                        Text(
                            text = "Erro: ${clusterInfo.error}",
                            color = MaterialTheme.colorScheme.error
                        )
                    } else if (clusterInfo.isConnected) {
                        Text("Status: Conectado")
                        Text("Server: ${clusterInfo.serverUrl}")
                        Text("Versão: ${clusterInfo.version}")
                    } else {
                        Text("Clique no botão para obter informações do cluster")
                    }
                }
            }
        }
    }
}