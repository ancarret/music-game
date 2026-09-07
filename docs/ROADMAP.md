# Roadmap — Music Career Simulator

Guía de fases. No es un calendario rígido: el orden importa más que las fechas. Cada paso se marca
`[x]` cuando está hecho. "Siguiente paso" siempre es el primer `[ ]` sin marcar dentro de la fase activa.

Este documento manda en **qué toca ahora**. Para **hacia dónde vamos** (visión completa del juego,
sistemas futuros, mecánicas), ver `DISENO_JUEGO.md`.

Fase activa: **2 — Java básico + POO a través del juego**

---

## Fase 0 — Punto de partida
- [x] Repo Git creado
- [x] Comprobado entorno en Mac (novia): JDK 21, Git, Maven, VS Code + extensiones Java — todo OK
- [x] `CLAUDE.md` del proyecto creado (contrato de trabajo)
- [x] Carpeta `docs/` creada (roadmap + apuntes)
- [x] Comprobado y preparado entorno en PC Windows (personal): JDK 21 (Liberica Full) y VS Code
      Extension Pack for Java instalados vía winget/CLI; Git ya estaba. `JAVA_HOME` sin setear
      (no hace falta hasta Fase 5, igual que en el Mac)

## Fase 1 — Entorno de desarrollo
- [x] Crear la primera clase Java "a mano" (sin Maven todavía) y ejecutarla desde terminal
- [x] Entender qué hace `javac` vs `java` (ver `docs/apuntes/02-compilar-vs-ejecutar.md`)
- [x] Primer commit con esa clase

## Fase 2 — Java básico + POO a través del juego (consola, sin persistencia)

Capas del personaje según `diseno/01-personaje.md` §7. Cada capa deja el juego ejecutable.

- [x] **Capa 1** — Clase `Player`: 6 campos privados, constructor, método de resumen
      → clase, campos, tipos, constructor, encapsulación
- [ ] **Capa 2** — El instrumento deja de ser texto libre → `enum` · **EN CURSO**
      + valores iniciales fuera del constructor (`static final`) y elección por consola (`Scanner`)
- [ ] **Capa 3** — Acciones `practicar` y `avanzar día` → métodos con comportamiento y validaciones
- [ ] **Capa 4** — Varias habilidades a la vez → colecciones (`Map`)
- [ ] **Capa 5** — Los seis atributos de talento → primer objeto de valor
- [ ] **Capa 6** — Repertorio de canciones (covers del dataset semilla) → `List`, relaciones entre entidades
- [ ] **Capa 7** — Primera actuación callejera con su fórmula → lógica de simulación + azar controlado
- [ ] **Capa 8** — Bucle principal de juego en consola → entrada de usuario, control de flujo
- [ ] **Capa 9** — Refactor: extraer `Wallet`, `SkillSet`, `Audience` de `Player` → detectar la clase-Dios
- [ ] Introducir excepciones cuando aparezca un caso real que las necesite

## Fase 3 — Testing
- [ ] JUnit: primeros tests sobre lógica ya existente (no antes de tener lógica)
- [ ] Diferenciar unit test vs integration test
- [ ] Mockito, solo cuando exista una dependencia real que mockear

## Fase 4 — Refactor y Git más avanzado
- [ ] Detectar código que "empieza a doler" y refactorizar con motivo
- [ ] Branches, commits pequeños, mensajes de commit decentes

## Fase 5 — Spring Boot
- [ ] Qué problema resuelve Spring (IoC / DI / beans) antes de usar anotaciones
- [ ] Migrar el dominio ya existente a un proyecto Spring Boot (Maven)
- [ ] Controllers / Services — separar responsabilidades con motivo real

## Fase 6 — API REST
- [ ] Primer endpoint GET
- [ ] POST / PUT / PATCH / DELETE, status codes correctos
- [ ] DTOs (entidad de dominio ≠ lo que expone la API)
- [ ] Validación de entrada, manejo de errores

## Fase 7 — Base de datos
- [ ] SQL básico (antes de esconderlo detrás de JPA)
- [ ] Spring Data JPA / Hibernate: entidades, relaciones, transacciones
- [ ] Migraciones (Flyway o similar)
- [ ] Testing de integración contra base de datos real (Testcontainers cuando tenga sentido)

## Fase 8 — Backend "profesional"
- [ ] Manejo global de excepciones
- [ ] Logging
- [ ] Configuración por entornos (application-*.yaml, profiles)
- [ ] Spring Security (auth/autorización)
- [ ] OpenAPI / Swagger
- [ ] Docker + Docker Compose (app + PostgreSQL)
- [ ] CI/CD (GitHub Actions)
- [ ] Nociones básicas de despliegue/cloud

## Fase 9 — Frontend (mucho después)
- [ ] A definir cuando lleguemos — no es prioridad

---

## 📌 Retomar aquí (última sesión: 2026-09-06)

**Misión activa: Capa 2.** En `src/`, por este orden y compilando entre pasos:

1. Arreglar `printSummary()` en `Player.java`: falta un `+` antes de `" puntos de habilidad."`,
   falta imprimir `age`, faltan `energy` y `money`, y el `if` usa `=` en vez de comparar contenido
   con `.equals()`.
2. Crear `Instrument.java` con un `enum` de los tres instrumentos (guitarra acústica, piano,
   batería) y cambiar el tipo del campo en `Player`.
3. Sacar del constructor lo que no elige el jugador: 16 años, habilidad 40, energía 100, 150 €
   como constantes `static final`. El constructor solo recibe **nombre e instrumento**.
4. En `Main.java`, pedir nombre e instrumento por consola con `Scanner` (menú numérico) y crear
   el `Player` con la respuesta.

Sintaxis de todo esto en `apuntes/03-clases-objetos-y-tipos.md`.

**Pendiente de git:** el trabajo del día está sin commitear (diseño completo, apuntes, `Player.java`,
config de VS Code). Conviene commitear antes de cambiar de máquina.

---

## Ideas futuras aparcadas (scope creep controlado)

Todas las ideas de juego (managers, bandas, contratos, tipos de fama, conciertos cinemáticos, etc.)
viven ahora en `DISENO_JUEGO.md`, organizadas por sistema. Cuando surja una idea nueva sobre la
marcha, se anota **allí** en la sección que le corresponda y se sigue con la tarea activa.
