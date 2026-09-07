# 01 — El personaje

Modelo completo del personaje jugable. **Esto es el destino**: la clase no se escribe entera de golpe,
se construye por capas (ver sección 7).

Los campos se describen en lenguaje de dominio (nombre, qué significa, rango). Elegir el tipo Java y
la estructura concreta forma parte del ejercicio.

---

## 1. Identidad

| Campo | Rango / formato | Qué representa |
|---|---|---|
| `name` | texto | Nombre real del personaje |
| `stageName` | texto (opcional) | Nombre artístico. Puede cambiarse una vez por año; cambiarlo tarde cuesta audiencia |
| `age` | 6 – 80 | Edad actual. Empieza en 6 (prólogo) y salta a 16 |
| `birthDate` | fecha del calendario del juego | Para calcular edad y cumpleaños (evento) |
| `homeCity` | referencia a ciudad | Determina qué locales y oportunidades hay cerca |
| `preferredGenre` | referencia a género | Género con el que el personaje se identifica. Da bonus de calidad al componer en él |

## 2. Talento (atributos innatos)

Rango **1 – 100**. Se generan en la creación del personaje y **casi no cambian**: son el techo natural.
Solo suben mediante eventos vitales y formación, y como máximo **+15 en toda la partida**. Son lo que
hace que dos partidas se sientan distintas.

| Atributo | Qué afecta |
|---|---|
| `musicality` | Oído y sentido musical. Multiplica la calidad de toda interpretación y acelera el aprendizaje de cualquier instrumento |
| `creativity` | Techo de calidad al componer y probabilidad de ideas excepcionales |
| `charisma` | Presencia escénica, conversión de público en fans, negociación, relaciones |
| `discipline` | Velocidad de aprendizaje por sesión y resistencia al abandono en rachas malas |
| `stamina` | Energía máxima y velocidad de recuperación |
| `businessSense` | Calidad de las decisiones de negocio: contratos, precios, promoción, detectar cláusulas abusivas |

**Generación**: media 40, la mayoría entre 25 y 60, con posibilidad rara de 80+. Las decisiones del
prólogo (infancia) reparten hasta 25 puntos extra entre ellos. Detalle en la sección 6.

## 3. Habilidades

Rango **0 – 100**. Suben con la práctica, con **rendimientos decrecientes** fuertes. Son lo que el
jugador construye activamente.

### 3.1. Instrumentales
`acousticGuitar` · `electricGuitar` · `piano` · `drums` · `bass` · `singing`

### 3.2. De carrera
| Habilidad | Qué afecta |
|---|---|
| `songwriting` | Calidad de composición de canciones propias |
| `production` | Calidad de grabación y mezcla; permite autoproducirse y ahorrar dinero |
| `performance` | Calidad en directo: dominio del escenario, aguantar imprevistos, conectar con el público |
| `marketing` | Alcance de la promoción y crecimiento en redes |
| `networking` | Probabilidad de conseguir contactos, colaboraciones y oportunidades de industria |

### 3.3. Fórmula de progresión

```
gananciaPorSesión = 2.5
                  × (discipline / 50)
                  × ((100 − nivelActual) / 100) ^ 0.8      ← rendimientos decrecientes
                  × factorEnergía
                  × factorEstrés
                  × (1 + musicality/200)                    ← solo instrumentos
                  × aleatorio(0.85 … 1.15)
```

| Factor | Valor |
|---|---|
| `factorEnergía` | energía > 70 → 1.10 · energía 30-70 → 1.00 · energía < 30 → 0.60 |
| `factorEstrés` | estrés < 40 → 1.00 · estrés 40-70 → 0.85 · estrés > 70 → 0.65 |

Con `discipline` 50 y sin modificadores, esto da aproximadamente:

| Nivel actual | Ganancia por sesión | Sesiones para el siguiente tramo |
|---|---|---|
| 0 → 20 | ~2,3 | ~9 sesiones |
| 20 → 50 | ~1,9 | ~17 sesiones |
| 50 → 80 | ~1,2 | ~32 sesiones |
| 80 → 95 | ~0,5 | ~40 sesiones |
| 95 → 100 | ~0,2 | prácticamente inalcanzable sin talento alto |

Es deliberado: llegar a "competente" (50) es rápido y satisfactorio; llegar a "excepcional" (90+) es
una inversión de años que obliga a renunciar a otras cosas.

### 3.4. Bandas de nivel (para textos y desbloqueos)
0-19 principiante · 20-39 aficionado · 40-59 competente · 60-74 bueno · 75-89 profesional ·
90-97 virtuoso · 98-100 leyenda.

## 4. Recursos

| Recurso | Rango | Comportamiento |
|---|---|---|
| `energy` | 0 – energyMax | Se gasta con cada acción y se recupera durmiendo. `energyMax = 80 + stamina/5` (80-100) |
| `stress` | 0 – 100 | Sube al encadenar días exigentes o al tener contratos con presión. Penaliza aprendizaje, calidad e inspiración. Por encima de 85, riesgo diario de evento de burnout |
| `inspiration` | 0 – 100 | Necesaria para componer bien. Se consume al componer, se recupera descansando, escuchando música, viajando, viviendo experiencias y con eventos |
| `money` | ≥ 0 (puede haber deuda ≥ -2.000 €) | Euros. Ver `07-economia.md` |
| `health` | 0 – 100 | Baja con burnout, excesos y edad avanzada. Afecta a `energyMax` y a la disponibilidad de acciones |

**Recuperación diaria de energía** (al dormir): `+45 + stamina/4`, modificado por calidad de la
vivienda (×0,9 a ×1,2) y por estrés (estrés > 70 → ×0,8).

## 5. Estado de carrera y posesiones

Estos bloques **no viven todos dentro de la clase del personaje**: la mayoría acabarán siendo clases
propias que el personaje referencia. Decidir qué se queda dentro y qué sale es uno de los ejercicios
de diseño más importantes del proyecto.

| Bloque | Contenido | Documento |
|---|---|---|
| Audiencia | seguidores por segmento, popularidad, credibilidad, reputación de industria, reputación local por ciudad | `05-audiencia-y-fama.md` |
| Repertorio | canciones aprendidas (covers) con su nivel de familiaridad, canciones propias en cualquier fase | `03-musica.md` |
| Discografía | singles, EPs, álbumes publicados y sus métricas | `03-musica.md` |
| Equipamiento | instrumentos poseídos (con calidad y desgaste), amplificación, grabación, transporte | `07-economia.md` |
| Vestuario | prendas, estilo dominante, coherencia con el género | `07-economia.md` |
| Propiedades | vivienda actual, estudio propio | `07-economia.md` |
| Vínculos | banda actual, manager, productores habituales, sello, relaciones con otros artistas | `06-industria.md` |
| Contratos | contratos activos y sus obligaciones pendientes | `06-industria.md` |
| Historial | hitos, premios, conciertos dados, decisiones importantes | `05-audiencia-y-fama.md` |

## 6. Creación del personaje (prólogo a los 6 años)

El prólogo son **4 decisiones** narrativas. No es un menú de creación de personaje al uso: se juega.

1. **El primer instrumento** (regalo de los padres): guitarra acústica · piano · batería.
   Determina la habilidad con ventaja inicial y condiciona las primeras oportunidades a los 16.
2. **Cómo practicabas de niño**: a rajatabla cada tarde (+8 `discipline`) · cuando te apetecía, pero
   inventando cosas (+8 `creativity`) · tocando para quien se dejara (+8 `charisma`).
3. **Qué escuchabas en casa**: determina `preferredGenre` y otorga +5 `musicality`.
4. **Cómo era tu adolescencia temprana**: deporte y calle (+6 `stamina`) · encerrado escuchando y
   copiando canciones (+6 `musicality`) · trapicheando y vendiendo cosas en el instituto
   (+6 `businessSense`).

### 6.1. Estado inicial a los 16 años

**Decisión de diseño (revisada):** 10 años tocando un instrumento son muchos. El juego no debe
empezar "desde cero" ni obligar a años de grindeo antes de que pase algo interesante — el personaje
llega a los 16 ya siendo bueno en lo suyo, aunque le falte todo lo demás (escenario, banda, negocio).

| Concepto | Valor |
|---|---|
| Instrumento elegido en la infancia | 65 – 75 |
| `singing` | 45 – 55 |
| Resto de instrumentos | 0 – 5 |
| `songwriting` | 8 – 15 |
| `performance` | 10 – 18 |
| `production`, `marketing`, `networking` | 0 – 8 |
| `money` | 150 € |
| `energy` | al máximo |
| `stress` | 10 |
| `inspiration` | 60 |
| Seguidores | 0 |
| Equipamiento | el instrumento de la infancia, gama baja y con desgaste |

### 6.2. Ventaja del instrumento inicial

| Instrumento | Ventaja | Limitación |
|---|---|---|
| Guitarra acústica | Tocar en la calle desde el día 1, transporte trivial, camino natural a la eléctrica | Menos impacto en géneros electrónicos y urbanos |
| Piano | Mayor bonus a `songwriting` (+15% calidad de composición) y mejor base teórica (aprende otros instrumentos un 10% más rápido) | Tocar en la calle requiere un teclado portátil (250 €) y una batería externa |
| Batería | Mejor `stamina` efectiva en directo y muy demandado: acceso temprano a bandas y trabajo de sesión | No puede tocar en la calle en solitario de forma realista; necesita el camino de las bandas |

## 7. Plan de construcción de la clase (capas)

El orden en que se programa el personaje. **Cada capa existe para enseñar un concepto**, y solo se
aborda cuando el juego la necesita. Este es el guion de las próximas misiones.

| Capa | Qué se añade | Concepto que enseña |
|---|---|---|
| **1** | Identidad mínima + un instrumento + una habilidad + energía + dinero | Clase, campos, tipos primitivos, constructor, encapsulación |
| **2** | El instrumento como tipo cerrado en vez de texto libre | `enum`, por qué un tipo restringe estados imposibles |
| **3** | Acción de practicar y avanzar el día | Métodos con comportamiento, mutación controlada del estado, validaciones |
| **4** | Varias habilidades a la vez | Colecciones (`Map`), iteración, evitar 11 campos casi idénticos |
| **5** | Los seis atributos de talento | Agrupar datos que viajan juntos → primer objeto de valor |
| **6** | Repertorio de canciones aprendidas | `List`, relación entre entidades, composición |
| **7** | Inventario de equipamiento y vestuario | Composición frente a herencia, interfaces |
| **8** | Extraer `Wallet`, `SkillSet`, `Audience`, `Energy` de la clase | Refactor real: detectar la clase-Dios y repartir responsabilidades |
| **9** | Historial y estado de carrera | Inmutabilidad, eventos, por qué no todo debe ser mutable |

**Regla:** al terminar cada capa el juego debe seguir ejecutándose. Nada de "lo dejo a medias y
sigo".

## 8. Rasgos (sistema posterior)

Modificadores permanentes que se ganan jugando, no eligiendo. Ejemplos ya decididos:

| Rasgo | Cómo se consigue | Efecto |
|---|---|---|
| *Rata de escenario* | 50 actuaciones en directo | −50% penalización por nervios, +5 `performance` efectivo |
| *Perfeccionista* | Dedicar >20 sesiones a una misma canción | +10% calidad final, −15% velocidad de producción |
| *Prolífico* | Componer 30 canciones | +1 idea extra por sesión de composición, −5% calidad media |
| *Quemado* | Superar 90 de estrés tres veces | −20% recuperación de energía hasta que se trate |
| *Cara conocida* | 10.000 seguidores en una ciudad | Mejores condiciones en los locales de esa ciudad |
| *Vendido* | Firmar un major renunciando a todo el control creativo | −15 credibilidad artística, +20% ingresos |

## 9. Ideas aparcadas para este sistema

- Relaciones personales (pareja, familia, amistades) con efecto en estrés e inspiración.
- Salud mental como sistema propio más allá del estrés.
- Envejecimiento real: pérdida de `stamina` a partir de los 45, ganancia de `businessSense` y respeto.
- Vicios y excesos con riesgo/recompensa (creatividad a corto plazo, salud a largo).
- Personalización visual del personaje (para el frontend futuro).
- **Confianza y experiencia como recursos propios**, distintos de la habilidad técnica. La habilidad
  dice si tocas bien; la confianza diría si te atreves a hacerlo delante de gente y si aguantas la
  presión. Empezarían bajas y subirían actuando (calle, bares, familia) y bajarían con actuaciones
  fallidas o con minijuegos de directo mal resueltos (ver `04-directos.md` §3.2). Un umbral de
  confianza/experiencia (ej. 75) podría ser requisito para oportunidades como el primer
  bolo con una banda — encajaría como puerta adicional junto a los tiers de audiencia
  (`05-audiencia-y-fama.md`). Pendiente: decidir si merece campos propios o si es una lectura derivada
  de `performance` + historial de actuaciones recientes (más simple, menos estado que mantener).
