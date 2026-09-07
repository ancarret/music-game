# Diseño del juego — Music Career Simulator

Documento raíz del diseño. Aquí está la visión, los principios y el bucle central; el detalle de cada
sistema vive en `docs/diseno/`.

**Cómo se usa este diseño:**
- Es el **destino**, no el orden de construcción. El "qué toca ahora" lo manda siempre `ROADMAP.md`.
- Está escrito en **lenguaje de dominio, no en Java**: tablas de campos, rangos y fórmulas. Traducir
  eso a clases, tipos y estructuras Java es justo el ejercicio de aprendizaje.
- Los números concretos (costes, precios, pesos de fórmulas) son **decisiones tomadas para poder
  programar**, no dogma. Se ajustarán al balancear. Ver `diseno/08-simulacion.md`.
- Las ideas nuevas se anotan en el documento del sistema al que pertenezcan, y se sigue con la tarea
  activa.

## Índice de sistemas

| Documento | Contenido |
|---|---|
| [01-personaje.md](diseno/01-personaje.md) | Modelo completo del personaje, creación, talento, habilidades, rasgos, y el plan de construcción por capas |
| [02-tiempo-y-acciones.md](diseno/02-tiempo-y-acciones.md) | Calendario, franjas del día, energía, estrés, inspiración, catálogo de acciones |
| [03-musica.md](diseno/03-musica.md) | Canciones, covers, composición, grabación, producción, calidad, géneros, tendencias, lanzamientos |
| [04-directos.md](diseno/04-directos.md) | Calle, bares, salas, festivales, giras, setlists, fórmulas de actuación |
| [05-audiencia-y-fama.md](diseno/05-audiencia-y-fama.md) | Seguidores, segmentos de fans, popularidad, credibilidad, viralidad, prensa, premios |
| [06-industria.md](diseno/06-industria.md) | Managers, productores, discográficas, contratos, bandas, colaboraciones |
| [07-economia.md](diseno/07-economia.md) | Ingresos, gastos, equipamiento, ropa, vivienda, estudios, merchandising |
| [08-simulacion.md](diseno/08-simulacion.md) | Cómo se modela el azar, curvas, balanceo, reproducibilidad |
| [09-datos.md](diseno/09-datos.md) | Esquema de datos de artistas/canciones reales y estrategia de obtención |

---

## 1. Pitch

Simulador de gestión y progresión de carrera musical. El personaje empieza a los 6 años recibiendo su
primer instrumento, salta a los 16 con una ventaja construida durante la infancia, y desde ahí el
jugador administra tiempo, energía y dinero para convertir a alguien que toca por monedas en la calle
en el artista que decida llegar a ser.

Inspiración: Youtubers Life, modos carrera, tycoons, Football Manager. El núcleo no son los gráficos:
es la **simulación**.

## 2. Los cinco principios que no se rompen

### 2.1. Esfuerzo + calidad + incertidumbre
El esfuerzo desplaza la probabilidad, nunca la fija. Una canción trabajada meses tiene una
distribución de resultados mucho mejor que una hecha deprisa, pero sigue siendo una distribución:
puede fracasar. Y una canción sencilla puede explotar.

Traducción técnica: todo resultado importante se calcula como `valor determinista → distribución →
tirada`. Nunca `calidad = horas * 10`. Ver `diseno/08-simulacion.md`.

### 2.2. Decisiones con coste de oportunidad
Cada día tiene 3 franjas y una reserva de energía. Practicar es no componer. Trabajar es no ensayar.
Aceptar un contrato es perder control creativo. El juego es interesante por lo que el jugador
**renuncia**, no por lo que acumula.

### 2.3. Ninguna variable única lo resume todo
No existe un `fame` global. Existen popularidad mainstream, credibilidad artística, reputación de
industria y fans de distintos tipos, y **pueden ir en direcciones opuestas**. Vender mucho puede
costar credibilidad. Ser respetado por la crítica no paga el alquiler.

### 2.4. Muchos finales legítimos
No hay un único camino "bueno". Ver arcos de carrera (sección 5). Un artista underground con 200.000
fans devotos es un final tan válido como una estrella global con 30 millones de oyentes casuales, y
el juego debe hacer que se **sientan** distintos.

### 2.5. El dominio no se acopla a datos reales
`Artist`, `Song` y `Genre` son entidades genéricas; los artistas reales son solo filas de datos. Debe
bastar con cambiar el dataset para publicar el juego sin tocar una línea de lógica. Nunca
`if (artistName.equals("..."))`.

---

## 3. El bucle central

```
   ┌─────────────────────────────────────────────────┐
   │  DÍA                                            │
   │  3 franjas (mañana / tarde / noche)             │
   │  Energía disponible                             │
   └──────────────────┬──────────────────────────────┘
                      ↓
        Elegir acción por franja
        (practicar · componer · ensayar · trabajar ·
         estudiar · tocar en la calle · grabar ·
         actuar · promocionar · descansar · social)
                      ↓
        Se consumen tiempo y energía
                      ↓
        Se resuelve la acción
        (determinista + tirada de azar)
                      ↓
        Cambian: habilidades · dinero · audiencia ·
        reputación · calidad de obra · relaciones
                      ↓
        Posible evento aleatorio
        (oportunidad · contratiempo · propuesta)
                      ↓
        Avanzar día → recuperar energía → repetir
```

Sobre ese bucle diario se montan ciclos largos: aprender canciones → actuar → ganar audiencia →
componer → grabar → publicar → crecer → decisiones de industria → giras y álbumes.

## 4. Ejes de progresión

El personaje avanza simultáneamente en cinco ejes que **no** se mueven al mismo ritmo. Casi todos los
dilemas del juego nacen de que un eje avanza a costa de otro.

| Eje | Qué mide | Cómo sube | Qué lo frena |
|---|---|---|---|
| **Técnico** | Habilidades musicales | Práctica y ensayo | Tiempo, energía, rendimientos decrecientes |
| **Creativo** | Calidad de la obra propia | Composición, inspiración, producción | Inspiración agotada, prisas, presión de contrato |
| **Audiencia** | Gente que te escucha | Actuar, publicar, promocionar, viralidad | Decay de fans casuales, silencio prolongado |
| **Económico** | Dinero y recursos | Actuaciones, streams, contratos, merch | Gastos fijos, equipo, inversión en calidad |
| **Industria** | Puertas que se abren | Reputación, contactos, contratos | Malas decisiones, incumplir contratos, quemar puentes |

## 5. Arcos de carrera

No son clases cerradas que se eligen: son **estados a los que se llega** por acumulación de
decisiones. El juego los reconoce y ajusta las oportunidades que ofrece.

| Arco | Cómo se llega | Se siente como |
|---|---|---|
| **Independiente** | Publicar sin sello, financiarse con directos y merch | Menos dinero, control total, crecimiento lento y sólido |
| **Underground** | Alta credibilidad, baja popularidad mainstream, género de nicho | Comunidad pequeña y devota, salas llenas de 400 personas, prensa especializada |
| **Estrella de major** | Firmar con discográfica grande, priorizar alcance | Mucho dinero y exposición, calendario asfixiante, credibilidad en riesgo |
| **Miembro de banda** | Formar/entrar en banda y mantenerla | Ingresos y decisiones compartidos, conflictos, resultados por encima de tu habilidad individual |
| **Ex-banda en solitario** | Salir de una banda con audiencia ya construida | Empiezas con audiencia heredada pero expectativas y comparaciones |
| **Compositor / productor de otros** | Songwriting y producción altos, poca exposición propia | Dinero estable y respeto de industria sin ser famoso |
| **Músico de sesión** | Habilidad instrumental muy alta, poca obra propia | Sueldo fiable, cero fama, red de contactos enorme |
| **Meteoro** | Un viral temprano sin base técnica | Subida brutal y caída si no consolidas |

## 6. Tiers de audiencia

Estado derivado (no editable) que actúa como llave de contenido. Ver `diseno/05-audiencia-y-fama.md`.

| Tier | Seguidores | Se desbloquea |
|---|---|---|
| Busker | 0 – 500 | Calle, micro abierto |
| Local Act | 500 – 5.000 | Bares, teloneo local, primer EP |
| Emerging | 5.000 – 50.000 | Salas pequeñas, prensa musical, ofertas de sellos indie |
| Established | 50.000 – 500.000 | Salas medianas, festivales, majors, giras nacionales |
| Star | 500.000 – 5.000.000 | Grandes festivales, giras internacionales, premios |
| Icon | 5.000.000+ | Estadios, legado, papel de mentor en la industria |

## 7. Qué NO es este juego

Delimitar también es diseñar:

- **No es Guitar Hero.** Los minijuegos de ritmo son puntuales y modifican poco el resultado. El peso
  está en la preparación.
- **No es un clicker.** Repetir la acción óptima debe volverse aburrido y poco rentable a propósito
  (rendimientos decrecientes, decay de audiencia, fatiga).
- **No es un simulador económico realista.** Los números buscan ser legibles y divertidos, no exactos.
- **No es una novela visual.** Hay narrativa y eventos, pero la columna vertebral es la simulación.
- **No es multijugador.** Un solo personaje, una sola línea temporal.

## 8. Estado del diseño

**Decidido y listo para programar:** modelo del personaje, calendario y energía, catálogo de acciones,
sistema de habilidades, actuación callejera, canciones y calidad, economía inicial, tiers.

**Decidido a grandes rasgos, con detalle pendiente al llegar:** contratos, bandas, giras, premios,
prensa, merchandising.

**Abierto a propósito:** balanceo fino de todas las fórmulas (solo se puede ajustar jugando), catálogo
completo de eventos aleatorios, dataset final de artistas y canciones.
