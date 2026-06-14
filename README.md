# HS LEWIS FOG - App Android Nativo (Kotlin)

Aplicativo Android nativo desenvolvido em **Kotlin** com máxima performance e controle total.

## Funcionalidades

- **HS PRECISION**: Injeção de código com headshot_multiplier=2.5, recoil_reduction=0.85, aim_assist=1.8
- **HS PESCOÇO**: Injeção de código com neck_hit_multiplier=3.0, head_zone_expansion=1.5, auto_aim_neck=true
- **Autenticação**: Sistema seguro com senha LEWISFOV
- **Status em Tempo Real**: Indicadores visuais de ativação
- **Integração com Backend**: Comunicação HTTP com o servidor

## Requisitos

- Android Studio 2022.1 ou superior
- Android SDK 24+ (Android 7.0+)
- Java 8 ou superior
- Gradle 7.6+

## Instalação e Compilação

### 1. Abrir o Projeto
```bash
cd HSLewisApp
# Abra em Android Studio: File > Open > HSLewisApp
```

### 2. Compilar
```bash
./gradlew build
```

### 3. Gerar APK
```bash
./gradlew assembleRelease
```

O APK será gerado em: `app/build/outputs/apk/release/app-release.apk`

### 4. Instalar no Dispositivo
```bash
adb install app/build/outputs/apk/release/app-release.apk
```

## Estrutura do Projeto

```
HSLewisApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/lewisfog/hslewis/
│   │       │   └── MainActivity.kt
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   └── activity_main.xml
│   │       │   ├── values/
│   │       │   │   ├── strings.xml
│   │       │   │   ├── colors.xml
│   │       │   │   └── themes.xml
│   │       │   └── drawable/
│   │       │       ├── button_bg.xml
│   │       │       ├── button_green.xml
│   │       │       ├── button_red.xml
│   │       │       ├── edit_text_bg.xml
│   │       │       └── card_bg.xml
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Credenciais

- **Senha**: LEWISFOV

## Tecnologias Utilizadas

- **Kotlin** - Linguagem de programação
- **Android SDK** - Framework Android
- **OkHttp** - Cliente HTTP
- **Gson** - Serialização JSON
- **Coroutines** - Programação assíncrona

## API Backend

O app se conecta ao backend em:
```
https://3000-ioxxvfet9guy9xbkpko1m-e6bcf4a6.us2.manus.computer/api/trpc
```

Endpoints utilizados:
- `risk.injectPrecision` - Injetar código de precisão
- `risk.deactivate` - Remover código injetado

## Permissões Necessárias

- `android.permission.INTERNET` - Acesso à internet
- `android.permission.ACCESS_NETWORK_STATE` - Verificar estado da rede

## Notas Importantes

- O app requer conexão com internet
- Compatível com Android 7.0 (API 24) ou superior
- Interface otimizada para dispositivos móveis
- Código 100% nativo em Kotlin

## Desenvolvimento

Para modificar o app:

1. Edite `MainActivity.kt` para lógica
2. Edite `activity_main.xml` para layout
3. Edite arquivos em `res/drawable/` para estilos
4. Recompile com `./gradlew build`

## Licença

MIT
