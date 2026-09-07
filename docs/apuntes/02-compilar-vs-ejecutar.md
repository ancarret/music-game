# Compilar vs Ejecutar

## Código fuente
Lo que tú escribes (`Main.java`) es **código fuente**: texto legible por humanos, pero el procesador
de tu ordenador no entiende Java, C++, Python ni nada de eso. El procesador solo entiende
**instrucciones máquina**: secuencias de bytes muy concretas para su arquitectura (x86, ARM...).

Hace falta algo que traduzca de un lenguaje al otro. Ahí entran los compiladores/intérpretes.

## Compilar
**Compilar** es traducir código fuente a otra forma, antes de ejecutarlo, mediante un programa
llamado **compilador**. En Java, el compilador es `javac`.

`javac Main.java` traduce `Main.java` a `Main.class`. Ese `.class` no es código máquina nativo:
es **bytecode**, un lenguaje intermedio inventado por Java, independiente del procesador y del
sistema operativo. Esto es justo lo que hace a Java "portable": el mismo `.class` funciona igual
en Mac, Windows o Linux, siempre que exista una JVM para esa plataforma.

Si el compilador encuentra un error (como el `;` que te faltaba), se para ahí y no genera el
`.class`. Por eso "compila" es sinónimo de "el código es sintácticamente válido", no de "el
programa funciona bien".

## Ejecutar
**Ejecutar** es hacer que ese código realmente corra: que el procesador vaya, instrucción a
instrucción, haciendo lo que dice el programa (aquí sumar, allá imprimir, más allá comparar...).

En Java, `java Main` arranca la **JVM** (Java Virtual Machine), que:
1. Carga el `Main.class`.
2. Va interpretando/ejecutando el bytecode (con optimizaciones internas tipo JIT que no necesitas
   ahora mismo).
3. Empieza por el método `main`, que es el punto de entrada.

## Por qué Java tiene este paso intermedio (bytecode) y otros lenguajes no
- **C / C++**: el compilador traduce directamente a código máquina nativo de tu procesador
  concreto. Rápido, pero el binario resultante solo sirve para esa arquitectura/SO exacta.
- **Python**: no se compila explícitamente. Un intérprete lee el código línea a línea y lo va
  ejecutando sobre la marcha (por eso "no hace falta compilar" en Python, aunque internamente
  también genera un bytecode invisible en `.pyc`).
- **Java**: híbrido. Compila una vez a bytecode (portable), y ese bytecode se ejecuta sobre una
  JVM específica de cada plataforma. "Compila una vez, ejecuta en cualquier sitio."

## Resumen de comandos que ya usaste
| Comando | Qué hace | Qué genera |
|---|---|---|
| `javac Main.java` | Compila el código fuente | `Main.class` (bytecode) |
| `java Main` | Ejecuta el bytecode en la JVM | Salida por consola |

Nota: en `java Main` se pone el nombre de la **clase**, sin extensión — no es un nombre de archivo,
es el nombre que la JVM busca dentro del `.class` para arrancar la ejecución.

## Con varias clases: `javac` busca las demás relativas a donde lo ejecutas, no al archivo

Error real: compilar `javac src/Main.java` **desde la raíz del proyecto** da
`error: cannot find symbol` sobre `Player` e `Instrument`, aunque esos archivos estén justo al lado
de `Main.java` dentro de `src/`.

Causa: al indicarle una ruta, `javac` busca las clases que le faltan a partir de la carpeta desde la
que se ejecuta el comando (la raíz), no a partir de la carpeta donde vive el archivo indicado
(`src/`). Como en la raíz no hay nada, no las encuentra.

Dos formas correctas:
```powershell
# Opción A: entrar en la carpeta y compilar todo lo que haga falta
cd src
javac Main.java Player.java Instrument.java
java Main

# Opción B: sin moverte, pasando la ruta completa de cada archivo
javac src/Main.java src/Player.java src/Instrument.java
```

Regla práctica: **cuando hay más de una clase, se listan todas en el mismo `javac`**. No confiar en
que las encuentre solo.
