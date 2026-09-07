# 05 — Audiencia, fama y reputación

Aquí se cumple el principio "ninguna variable única lo resume todo".

---

## 1. Las cuatro métricas independientes

| Métrica | Rango | Qué mide | Cómo sube | Cómo baja |
|---|---|---|---|---|
| `popularity` | 0 – 100 | Cuánto te conoce el público general | Lanzamientos con alcance, virales, promoción, TV/prensa masiva | Decae 1,5%/mes sin actividad |
| `artisticCredibility` | 0 – 100 | Cuánto te respetan como artista | Calidad alta real, riesgo creativo, géneros con `credibilityWeight` alto, coherencia | Vender el discurso, sobreexposición, discos flojos, exceso de covers |
| `industryReputation` | 0 – 100 | Cuánto confían en ti profesionales | Cumplir contratos, buenos directos, puntualidad, contactos, éxito comercial | Incumplir, cancelar, conflictos públicos, fracasos caros |
| `followers` | 0 – ∞ | Tamaño real de tu audiencia | Directos, lanzamientos, redes, virales | Decay de fans casuales |

**Pueden ir en direcciones opuestas y ese es el juego.** Firmar un major y sacar un disco comercial
sube `popularity` y `industryReputation` y baja `artisticCredibility`. Un disco experimental
arriesgado hace lo contrario.

## 2. Segmentos de fans

Los seguidores no son un número único:

| Segmento | Proporción típica | Comportamiento |
|---|---|---|
| **Casuales** | 70-85% | Escuchan lo que suena. Decaen **2,5%/mes** sin lanzamientos. Compran poco merch, van a festivales, no a tu concierto |
| **Fieles** | 12-25% | Van a tus conciertos, compran discos y merch. Decaen 0,3%/mes. Perdonan un disco malo |
| **Superfans** | 1-5% | Compran todo, viajan a otras ciudades, defienden en redes. No decaen. Se enfadan de verdad si les traicionas |

**Conversión**: los casuales suben a fieles con conciertos (asistir convierte un 15%), con
credibilidad alta y con constancia. Los fieles suben a superfans con momentos memorables y cercanía.

**Objetivo de diseño cumplido**: 200.000 seguidores con 40% de fieles llenan salas de 3.000 personas
en 20 ciudades. 2.000.000 de seguidores con 5% de fieles llenan lo mismo, pero generan diez veces más
streams. Dos carreras muy distintas con el mismo "éxito" aparente.

## 3. Reputación local

Por ciudad, 0 – 100. Sube tocando allí (calle, bares, conciertos) y baja lentamente por ausencia.
Determina: acceso a locales de esa ciudad, caché, asistencia y calidad del público.

Da lugar a carreras regionales: ser enorme en tu ciudad y desconocido a 300 km.

## 4. Redes sociales

| Concepto | Decisión |
|---|---|
| Seguidores en redes | Correlacionados con `followers`, pero no idénticos: se pueden tener muchos seguidores digitales y poca gente en los conciertos |
| Publicar | Acción de 8 de energía. Alcance = f(`marketing`, seguidores, calidad del contenido, azar) |
| Viralidad | Tirada de cola larga. Un vídeo puede multiplicar seguidores ×2 – ×20 en días |
| Riesgo | Los seguidores ganados por viral son casuales al 95% y decaen rápido si no hay obra que los retenga |
| Polémicas | Eventos que suben `popularity` y bajan `industryReputation` |

## 5. Prensa y crítica

A partir de Local Act, los lanzamientos reciben **reseñas** (0-100) que dependen de `finalQuality`,
`artisticCredibility`, expectativas y azar. Las reseñas mueven `artisticCredibility` y, en menor
medida, ventas.

**Expectativas**: cuanto mejor fue tu disco anterior, más dura es la crítica con el siguiente. Un
segundo disco igual de bueno que el primero recibe peores notas.

## 6. Premios

Ceremonias anuales por género y generales. Nominaciones a partir de Emerging.

| Elemento | Efecto |
|---|---|
| Nominación | +3 `industryReputation`, +5% ventas del disco nominado |
| Victoria | +8 `industryReputation`, +5 `popularity`, pico de ventas, entrada en el historial |
| Premio de crítica (nicho) | +10 `artisticCredibility` |
| Premio comercial (más vendido) | +8 `popularity`, −3 `artisticCredibility` |

## 7. Tiers

Derivados de `followers` (ver tabla en `DISENO_JUEGO.md`, sección 6). Actúan como llave para locales,
ofertas de sellos, prensa y eventos. Bajar de tier es posible y debe doler.

## 8. Historial

Registro append-only de hitos: primer bolo, primer viral, primer disco, primer nº1, premios, rupturas
de banda, contratos firmados. Alimenta la narrativa, el final de partida y el futuro portfolio del
jugador dentro del juego.

## 9. Ideas aparcadas para este sistema

- Fans por género: cambiar de estilo hace perder parte de la audiencia anterior.
- Audiencia por país/idioma y el salto internacional.
- Haters y detractores como métrica propia.
- Legado: lo que queda de ti 20 años después.
- **Subir covers a YouTube como acción propia** (distinta del viral pasivo de calle de
  `04-directos.md` §2.4): el jugador elige grabar y publicar una interpretación ya hecha, con
  reproducciones que crecen con el tiempo (`marketing`, calidad de interpretación, popularidad de la
  canción, azar de cola larga — reutilizando el mecanismo de `08-simulacion.md` §4). Un vídeo que
  despega podría disparar `followers` y generar el evento "una banda te propone unirte", conectando
  con la idea de confianza/experiencia aparcada en `01-personaje.md` §9.
