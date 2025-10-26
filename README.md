# KUI - Kubernetes UI Desktop

KUI é uma aplicação desktop moderna para gerenciamento e monitoramento de clusters Kubernetes, construída com Kotlin Multiplatform e Compose Multiplatform.

## 🎯 Objetivo

Fornecer uma interface gráfica intuitiva e eficiente para administradores e desenvolvedores gerenciarem recursos Kubernetes, oferecendo uma alternativa visual ao kubectl com funcionalidades avançadas de monitoramento e administração.

## ✨ Funcionalidades

- **Gerenciamento de Clusters**: Conectar e alternar entre múltiplos clusters Kubernetes
- **Visualização de Recursos**: Pods, Services, Deployments, ConfigMaps, Secrets e mais
- **Monitoramento em Tempo Real**: Status e métricas dos recursos
- **Logs e Eventos**: Visualização centralizada de logs e eventos do cluster
- **Interface Moderna**: UI responsiva construída com Compose Multiplatform

## 🏗️ Arquitetura

### Stack Tecnológico
- **Kotlin Multiplatform Desktop (JVM)** - Plataforma base
- **Compose Multiplatform** - Framework de UI declarativa
- **Kubernetes Java Client** - Comunicação com clusters K8s
- **ViewModel Pattern** - Gerenciamento de estado
- **Coroutines & Flow** - Programação assíncrona e reativa
- **Koin** - Injeção de dependência

### Estrutura do Projeto

* [/composeApp](./composeApp/src) - Código compartilhado da aplicação
  * [commonMain](./composeApp/src/commonMain/kotlin) - Código comum para todas as plataformas
    * `ui/screens/` - Telas da aplicação
    * `ui/components/` - Componentes reutilizáveis
    * `ui/theme/` - Tema e estilos
    * `viewmodel/` - ViewModels para gerenciamento de estado
    * `data/repository/` - Repositórios para acesso a dados
    * `data/model/` - Modelos de dados Kubernetes
    * `di/` - Configuração de injeção de dependência
  * [jvmMain](./composeApp/src/jvmMain/kotlin) - Código específico para Desktop (JVM)
    * `platform/` - Implementações específicas da plataforma

## 🚀 Desenvolvimento

### Pré-requisitos
- JDK 11 ou superior
- Acesso a um cluster Kubernetes (local ou remoto)
- kubectl configurado (opcional, para testes)

### Executando o Projeto

Para executar a aplicação em modo de desenvolvimento:

```bash
# macOS/Linux
./gradlew :composeApp:run

# Windows
.\gradlew.bat :composeApp:run
```

## 📁 Padrões de Código

- **ViewModels**: Herdam de `ViewModel`, usam `StateFlow` para estado da UI
- **Repositories**: Interface no `commonMain`, implementação no `jvmMain`
- **Composables**: Funções puras, recebem ViewModels como parâmetro
- **Modelos**: Data classes para entidades Kubernetes

## 🤝 Contribuição

Contribuições são bem-vindas! Por favor, siga os padrões de código estabelecidos e mantenha a documentação atualizada.

---

**Tecnologias**: [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html) • [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) • [Kubernetes](https://kubernetes.io/)