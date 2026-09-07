# Clases, objetos y tipos básicos

Conceptos de la primera sesión de código. Los ejemplos usan un dominio ajeno al juego (libros) a
propósito: si mañana no me acuerdo de la sintaxis, la copio de aquí y la traduzco yo.

---

## Clase vs objeto

- **Clase**: el molde. Define qué datos y qué comportamiento tiene *un tipo de cosa*. Se escribe una vez.
- **Objeto** (o **instancia**): una cosa concreta creada con ese molde. Puede haber miles.

`Player` es la clase; Andrés con guitarra y 150 € es un objeto.

**Regla del archivo**: una clase `public` llamada `Book` tiene que vivir en `Book.java`. El nombre
debe coincidir exactamente, mayúsculas incluidas.

## Campos y encapsulación

```java
public class Book {
    private String title;   // "private" = solo accesible desde dentro de esta clase
    private int pages;
}
```

`private` es **encapsulación**: nadie de fuera toca el estado directamente. Se accede a través de
métodos que la clase decide ofrecer. Sirve para que el objeto pueda garantizar que su estado siempre
es válido (que la energía no sea −40, por ejemplo).

## Constructor

Método especial que se ejecuta al crear el objeto y lo deja en un estado inicial válido.

```java
public Book(String title, int pages) {   // mismo nombre que la clase, SIN tipo de retorno
    this.title = title;                  // this.title = el campo; title = el parámetro
    this.pages = pages;
}
```

`this` significa "este objeto". Es obligatorio cuando el parámetro se llama igual que el campo,
porque si no, se estaría asignando el parámetro a sí mismo.

**Al constructor solo va lo que varía** entre objetos. Lo que siempre empieza igual se inicializa
dentro, preferiblemente con una constante:

```java
private static final int INITIAL_TIMES_READ = 0;
```

- `static` → pertenece a la clase, no a cada objeto (existe una sola copia).
- `final` → no se puede reasignar; es una constante.

Se escriben en MAYÚSCULAS_CON_GUIONES por convención. Sirven para evitar "números mágicos" sueltos
por el código.

## Métodos

```java
public void printSummary() {              // void = no devuelve nada
    System.out.println(title + " (" + pages + " páginas)");
}
```

Se llaman sobre un objeto: `b.printSummary();`

## Comparaciones: `==` vs `.equals()`

**La trampa número uno de Java.**

| Comparación | Qué usar |
|---|---|
| Números (`int`, `double`), `boolean` | `==` |
| Textos (`String`) y objetos en general | `.equals()` |
| Valores de un `enum` | `==` (también vale `.equals()`, pero `==` es lo idiomático) |

`==` sobre objetos pregunta *"¿son el mismo objeto en memoria?"*, no *"¿valen lo mismo?"*.

```java
String a = "hola";
String b = "hola";
a == b          // puede dar true POR CASUALIDAD (Java reutiliza literales iguales)
a.equals(b)     // true siempre, y es lo correcto
```

Es peligroso justamente porque a veces funciona: falla el día que el texto viene del teclado o de una
base de datos.

Con enums `==` sí es correcto porque solo existe un objeto por cada valor del enum.

Y ojo con confundir `=` (asignar) con `==` (comparar). `if (x = 5)` no compila en Java.

## Enums

Tipo con un **conjunto cerrado de valores posibles**. Su valor real es que hace imposible escribir un
estado inválido: con un `String` alguien puede escribir `"gitarra"`; con un enum, eso ni compila.

```java
public enum BookFormat {
    PAPERBACK,
    HARDCOVER,
    EBOOK
}
```

```java
private BookFormat format;
this.format = BookFormat.EBOOK;

if (format == BookFormat.EBOOK) { ... }

switch (format) {
    case PAPERBACK -> System.out.println("Tapa blanda");
    case HARDCOVER -> System.out.println("Tapa dura");
    case EBOOK     -> System.out.println("Digital");
}
```

Al imprimir un enum directamente sale su nombre en mayúsculas (`EBOOK`). Los enums pueden llevar
datos propios dentro para arreglar eso — pendiente de ver.

## Leer del teclado: `Scanner`

```java
import java.util.Scanner;    // arriba del todo del archivo

Scanner scanner = new Scanner(System.in);

System.out.print("Título: ");      // print no salta de línea; println sí
String title = scanner.nextLine();

System.out.print("Páginas: ");
int pages = scanner.nextInt();
scanner.nextLine();                // ← descarta el salto de línea pendiente
```

⚠️ **Trampa**: `nextInt()` lee el número pero deja el salto de línea sin consumir. El siguiente
`nextLine()` devuelve cadena vacía y parece que el programa se salta una pregunta. Solución: un
`nextLine()` de descarte después de cada `nextInt()`.

## Tipos básicos

| Tipo | Para qué |
|---|---|
| `int` | Enteros |
| `double` | Decimales |
| `boolean` | `true` / `false` |
| `String` | Texto (ojo: es un objeto, no un tipo primitivo — por eso `.equals()`) |

### `double` y el dinero

```java
System.out.println(0.1 + 0.2);   // 0.30000000000000004
```

`double` guarda decimales en binario y hay valores que no puede representar exactamente. En un
videojuego es tolerable; en backend financiero **nunca**: allí se usa `BigDecimal` o enteros de
céntimos.

## Concatenar texto

Con `+`. Hay que ponerlo entre **cada** trozo:

```java
System.out.println("Soy " + name + ", tengo " + age + " años");
```

Olvidarlo entre una variable y un literal es error de compilación. Olvidar la variable entera (dejar
`"tengo " + " años"`) **compila igual** e imprime mal: los errores que el compilador no ve son los
peligrosos.
