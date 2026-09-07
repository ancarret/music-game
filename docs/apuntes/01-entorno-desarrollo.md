# Entorno de desarrollo Java

## JDK (Java Development Kit)
Paquete que incluye:
- `javac`: compilador. Convierte `.java` (código fuente) en `.class` (bytecode).
- `java`: máquina virtual (JVM). Ejecuta el bytecode.
- Herramientas varias (jar, javadoc, debugger...).

Sin JDK no se puede ni compilar ni ejecutar Java.

## PATH
Variable de entorno del sistema operativo: lista de carpetas donde la terminal busca ejecutables.
Cuando escribes `java` en la terminal, el sistema recorre el PATH en orden hasta encontrar un
ejecutable llamado `java`. Por eso puede haber varios JDKs instalados y que "el que manda" sea el
primero que aparece en el PATH.

## JAVA_HOME
Variable de entorno que apunta a la carpeta raíz de un JDK concreto (ej.
`/Library/Java/JavaVirtualMachines/liberica-jdk-21-full.jdk/Contents/Home`). No la usa `java`/`javac`
directamente, pero sí la consultan herramientas de build (Maven, Gradle) e IDEs para saber qué JDK
usar para compilar el proyecto.

## Maven
Herramienta de gestión de dependencias y build para proyectos Java:
- Define una estructura de carpetas estándar (`src/main/java`, `src/test/java`...).
- Descarga librerías externas automáticamente (a partir de un archivo `pom.xml`).
- Compila, ejecuta tests y empaqueta la aplicación con comandos (`mvn compile`, `mvn test`, `mvn package`).

No hace falta para un único archivo `.java` suelto — ahí basta con `javac` + `java` a mano. Se
introduce cuando el proyecto tiene múltiples clases, dependencias externas o necesitamos empaquetarlo.

## Git
Control de versiones. Ya configurado en este repo (usuario `almuLR`, rama `main`).

## VS Code — Extension Pack for Java
Un solo paquete de extensiones que instala:
- `redhat.java`: soporte de lenguaje (autocompletado, errores en tiempo real).
- `vscjava.vscode-java-debug`: debugger.
- `vscjava.vscode-java-test`: runner de tests (JUnit).
- `vscjava.vscode-maven`: integración con Maven.
- `vscjava.vscode-java-dependency`: explorador de dependencias/proyecto.

## Estado comprobado (2026-09-06, Mac de prueba)
- JDK activo por defecto: Liberica JDK 21.0.6 LTS ✅ (correcto para Spring Boot)
- Maven usa internamente un JDK 23 de Homebrew (JAVA_HOME no seteado) — sin impacto todavía porque
  no usamos Maven en la Fase 1-2. Se resolverá con configuración local al proyecto cuando creemos el
  `pom.xml`.
- Git 2.49 ✅
- VS Code con Extension Pack for Java instalado ✅

## Estado comprobado (2026-09-06, PC Windows personal)
- Liberica JDK 21.0.12 Full ✅, instalado con `winget install BellSoft.LibericaJDK.21.Full`
- Git 2.52 ✅
- VS Code + Extension Pack for Java ✅ (`code --install-extension vscjava.vscode-java-pack`)
- `JAVA_HOME` sin setear — irrelevante hasta que aparezca Maven (Fase 5)
- Copilot y autocompletado desactivados en `.vscode/settings.json` solo para este repo: aquí el
  código lo escribo yo. IntelliSense sigue disponible a mano con `Ctrl+Space`.

## Checklist para una máquina nueva
1. `java -version` y `javac -version` → debe aparecer una versión 21.x. Si no, instalar un JDK 21 LTS.
2. `git --version` → si falta, instalar Git.
3. VS Code + extensión "Extension Pack for Java" (por ese nombre exacto en el marketplace).
4. Clonar/copiar este repo.

## El PATH se lee al arrancar, no en tiempo real
Error visto: tras instalar el JDK, la terminal seguía diciendo
`javac: The term 'javac' is not recognized...`.

Causa: el PATH se carga **cuando arranca el proceso**. Una terminal abierta antes de la instalación
conserva la lista antigua y para ella ese programa no existe.

Detalle importante: **abrir una terminal nueva dentro de VS Code no basta**, porque hereda el entorno
del proceso padre (el propio VS Code, que también arrancó antes). Hay que **cerrar y reabrir VS Code
entero**.

Parche para refrescar solo la terminal actual, sin cerrar nada:
```powershell
$env:Path = [Environment]::GetEnvironmentVariable('Path','Machine') + ';' + [Environment]::GetEnvironmentVariable('Path','User')
```

## Compilar con varias clases
Desde la carpeta que contiene los `.java`:
```powershell
cd src
javac Main.java Player.java
java Main
```
`java Main` lleva el nombre de la **clase**, sin extensión. Si se compila desde la raíz
(`javac src/Main.java`), el `.class` queda dentro de `src/` y `java Main` desde la raíz no lo
encuentra.
