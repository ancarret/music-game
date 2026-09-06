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

## Pendiente para otra máquina (ej. el Dell)
Al llegar a una máquina nueva, comprobar en orden:
1. `java -version` y `javac -version` → debe aparecer una versión 21.x. Si no, instalar un JDK 21 LTS.
2. `git --version` → si falta, instalar Git.
3. VS Code + extensión "Extension Pack for Java" (la busco por ese nombre exacto en el marketplace).
4. Clonar/copiar este repo.
