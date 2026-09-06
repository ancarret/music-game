# Roadmap — Music Career Simulator

Guía de fases. No es un calendario rígido: el orden importa más que las fechas. Cada paso se marca
`[x]` cuando está hecho. "Siguiente paso" siempre es el primer `[ ]` sin marcar dentro de la fase activa.

Fase activa: **1 — Entorno**

---

## Fase 0 — Punto de partida
- [x] Repo Git creado
- [x] Comprobado entorno en Mac (novia): JDK 21, Git, Maven, VS Code + extensiones Java — todo OK
- [x] `CLAUDE.md` del proyecto creado (contrato de trabajo)
- [x] Carpeta `docs/` creada (roadmap + apuntes)

## Fase 1 — Entorno de desarrollo
- [x] Crear la primera clase Java "a mano" (sin Maven todavía) y ejecutarla desde terminal
- [x] Entender qué hace `javac` vs `java` (ver `docs/apuntes/02-compilar-vs-ejecutar.md`)
- [ ] Primer commit con esa clase

## Fase 2 — Java básico + POO a través del juego (consola, sin persistencia)
- [ ] Representar el personaje jugable (atributos mínimos, a diseñar juntos cuando lleguemos)
- [ ] Representar el instrumento inicial (String / clase / enum — a decidir con criterio, no de oficio)
- [ ] Acción de practicar (consume energía, sube habilidad)
- [ ] Avance de día / bucle principal en consola
- [ ] Primera actuación callejera con resultado simple
- [ ] Ganar dinero y seguidores como consecuencia
- [ ] Introducir colecciones (List, Map...) cuando el juego lo pida de forma natural
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

## Ideas futuras aparcadas (scope creep controlado)
_(aquí anotamos cosas que se nos ocurran sobre la marcha para no perderlas ni desviarnos ahora)_

- Managers de artistas
- Sistema de bandas y colaboraciones
- Contratos discográficos con trade-offs (anticipo vs libertad creativa)
- Distinción entre popularidad / reputación de industria / credibilidad artística / fans fieles vs casuales
- Conciertos con componente cinemático + mini-eventos de ritmo puntuales
