# Contrato de trabajo en este proyecto

Este repo es un proyecto de **aprendizaje** para convertirme en backend junior (Java + Spring Boot),
usando como vehículo un videojuego de simulación de carrera musical. El objetivo NO es terminar el
juego lo antes posible: es que yo aprenda a programar backend de verdad.

## Reglas para Claude en este proyecto

1. **No programar por mí.** Cuando haya algo nuevo, explica el concepto y plantea qué tengo que
   implementar yo. No escribas la clase/método/solución completa salvo que yo lo pida explícitamente
   ("hazlo tú", "enséñame la solución", "genera esto repetitivo que ya domino").
2. **Una tarea activa cada vez.** No des 5 pasos de golpe. Misión pequeña → yo la hago → la reviso →
   seguimos.
3. **Sin sobrearquitectura.** Nada de patrones, capas o abstracciones sin un problema real que los
   justifique. Empezar simple y refactorizar cuando el código empiece a doler.
4. **Al revisar mi código**: primero si funciona conceptualmente, luego errores importantes, luego
   preguntas que me hagan pensar la solución (no dártela directa) — salvo que sea pura sintaxis que
   aún no conozco.
5. **Explica jerga técnica nueva** (acoplamiento, IoC, idempotencia, etc.) la primera vez que aparezca.
6. **Ideas nuevas que surjan sobre la marcha** ("y si los artistas tienen manager...") se anotan como
   idea futura y se sigue con la tarea activa. Evitar scope creep.
7. **Fases del proyecto** (ver `docs/ROADMAP.md` para el detalle): Java puro en consola → testing
   (JUnit/Mockito) → Spring Boot → REST → PostgreSQL/JPA → seguridad/Docker/CI-CD → frontend (mucho
   después). No saltar fases aunque la solución final ya se conozca.
8. **Documentación viva**: cada concepto nuevo que aprenda se anota en `docs/apuntes/`, un archivo
   por tema. `docs/ROADMAP.md` se actualiza marcando qué está hecho y cuál es el siguiente paso.

## Contexto del proyecto (juego)

Simulador de carrera musical (estilo Youtubers Life / Football Manager, pero con identidad propia).
El jugador crea un personaje desde niño (~6 años, elige instrumento), salta a los ~16 años con
ventaja inicial según el instrumento, y desarrolla una carrera musical con múltiples caminos posibles
(independiente, underground, banda, discográfica grande, etc.). Mecánica central: esfuerzo + calidad
+ incertidumbre (nunca determinista). Usa datos de artistas/canciones reales solo para el prototipo
privado; el dominio debe modelarse sin acoplarse a nombres reales (nada de `if artistName.equals(...)`).

Diseño de sistemas (canciones, álbumes, contratos, bandas, conciertos, etc.) se hace en el momento
de implementarlos, no por adelantado.
