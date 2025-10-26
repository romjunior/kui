# Projeto KUI - Kubernetes UI Desktop

## Arquitetura
- **Kotlin Multiplatform Desktop (JVM)**
- **Compose Multiplatform** para UI
- **ViewModel Pattern** para gerenciamento de estado
- **Kubernetes Client Library** para comunicação com clusters

## Estrutura de Pastas
```
composeApp/src/
├── commonMain/kotlin/
│   ├── ui/
│   │   ├── screens/          # Telas da aplicação
│   │   ├── components/       # Componentes reutilizáveis
│   │   └── theme/           # Tema e estilos
│   ├── viewmodel/           # ViewModels
│   ├── data/
│   │   ├── repository/      # Repositórios
│   │   └── model/          # Modelos de dados
│   └── di/                 # Injeção de dependência
└── jvmMain/kotlin/
    └── platform/           # Código específico JVM
```

## Padrões de Código

### ViewModels
- Herdar de `ViewModel`
- Usar `StateFlow` para estado da UI
- Separar lógica de negócio da UI
- Nomear como `[Feature]ViewModel`

### Repositories
- Interface no `commonMain`
- Implementação no `jvmMain` se necessário
- Usar Kubernetes client para operações

### Composables
- Funções puras quando possível
- Receber ViewModels como parâmetro
- Nomear com PascalCase
- Separar por funcionalidade

### Modelos
- Data classes para entidades Kubernetes
- Usar tipos nullable apropriados
- Mapear recursos K8s (Pods, Services, Deployments, etc.)

## Dependências Principais
- Compose Multiplatform
- Kubernetes Java Client
- Coroutines
- StateFlow/Flow
- Koin (DI)

## Convenções
- Usar camelCase para variáveis/funções
- PascalCase para classes/composables
- Prefixar interfaces com 'I' se necessário
- Código em inglês, comentários em português se necessário