# 08 — Simulación, azar y balanceo

Cómo se implementa el principio "esfuerzo + calidad + incertidumbre" sin que el juego sea ni
determinista ni una tómbola.

---

## 1. La regla de oro: tres pasos separados

Todo resultado importante se calcula así, **y en el código deben ser tres cosas separadas**:

```
1. VALOR DETERMINISTA   → lo que el jugador se ha ganado (habilidades, calidad, recursos)
2. DISTRIBUCIÓN         → qué rango de resultados abre ese valor
3. TIRADA               → dónde cae dentro del rango
```

Motivo técnico (importante para el futuro): si el paso 1 es una función pura sin azar, se puede
**testear con JUnit** comprobando valores exactos. Si el azar está mezclado dentro del cálculo, no se
puede testear nada. Esta separación es la razón por la que el diseño está escrito así.

## 2. Reproducibilidad

Toda la aleatoriedad del juego sale de **una única fuente controlada**, inicializada con una
**semilla**. Nunca se crea aleatoriedad suelta por ahí.

Consecuencias:
- Una partida con la misma semilla y las mismas decisiones da el mismo resultado → se pueden
  reproducir bugs.
- Los tests pueden fijar la semilla y verificar resultados concretos.
- En el futuro, permite guardar partida sin guardar el historial completo.

*Jerga nueva:* una **semilla** (seed) es el número con el que arranca el generador de números
pseudoaleatorios. Mismo número de partida → misma secuencia de "azar".

## 3. Tipos de tirada

| Tipo | Uso | Forma |
|---|---|---|
| **Ruido pequeño** | Práctica, propinas, interpretación | Multiplicador uniforme 0,85 – 1,15. Nunca cambia el resultado radicalmente |
| **Probabilidad de evento** | Ojeador, robo, oportunidad | Tirada porcentual simple contra un umbral |
| **Distribución centrada** | Talento inicial, `potential` de una canción | Valores medios frecuentes, extremos raros (suma de varias tiradas) |
| **Cola larga** | Éxito comercial, viralidad | Casi siempre modesto, rarísima vez brutal. Ver sección 4 |

## 4. La lotería del éxito

El mecanismo más importante del juego. Traduce "una canción trabajada puede fracasar y una sencilla
puede explotar".

```
probabilidadDeExplotar = 1% + (potential/100) × 4% + (finalQuality/100) × 3%
                         (entre 1% y 8%)

si explota:  multiplicador = 3 × … × 50 ×      (cuanto más raro, más alto)
si no:       multiplicador = 0.6 … 1.4         (ruido normal)

probabilidadDeFracaso = 15% − (finalQuality/100) × 10%
si fracasa:  multiplicador = 0.15 … 0.5
```

**Lectura del diseño:**
- Una canción de calidad 90 y potencial 85: ~7,5% de explotar, ~6% de fracasar. Normalmente le va
  bien, a veces la revienta, y de vez en cuando —injustamente— pasa desapercibida.
- Una canción de calidad 35: ~2,5% de explotar. Poco probable... pero **posible**. Ese 2,5% es lo que
  mantiene viva la ilusión y hace el juego interesante.

El esfuerzo no compra el resultado: compra **billetes de lotería mejores**.

## 5. Curvas usadas en el juego

| Curva | Dónde | Por qué |
|---|---|---|
| **Rendimientos decrecientes** `((100−x)/100)^0.8` | Habilidades, familiaridad, pulido de canciones | Evita el clicker: repetir la misma acción rinde cada vez menos |
| **Umbral con suelo** `max(0, (a−b) × k)` | Penalización por dificultad, conversión de fans | Por debajo de cierto nivel, el efecto simplemente no existe |
| **Techo por recurso** | Calidad limitada por el estudio o el presupuesto | Ningún esfuerzo compensa la falta de medios: hay que invertir |
| **Decay exponencial** `−x% mensual` | Fans casuales, popularidad, cola larga de streams | Nada se mantiene solo; parar es retroceder |
| **Escalón por tier** | Acceso a locales, sellos, prensa | Da sensación de hito al cruzar un umbral |

## 6. Números mágicos: dónde viven

**Prohibido** esparcir constantes por el código (`if (energia < 30)` repartido en quince sitios).

Plan progresivo, alineado con el aprendizaje:

| Fase | Dónde viven las constantes |
|---|---|
| Java puro (ahora) | Constantes con nombre dentro de la clase que las usa |
| Cuando se repitan | Una clase de configuración/reglas del juego centralizada |
| Cuando haya que balancear de verdad | Fichero externo de configuración, recargable sin recompilar |
| Con base de datos | Tablas de parámetros |

**Nunca antes de que el problema exista.** Pero cuando aparezca, es un ejercicio de refactor perfecto.

## 7. Balanceo

Objetivos concretos que el balanceo debe cumplir, para poder comprobar si el juego funciona:

| Hito | Tiempo de juego objetivo |
|---|---|
| Vivir de la música (dejar el trabajo) | 1 – 2 años de juego |
| Primeros 1.000 seguidores | 8 – 18 meses |
| Primer EP publicado | Año 2 |
| Tier Emerging | Año 3 – 4 |
| Tier Established | Año 6 – 9 (y no todas las partidas) |
| Tier Star | Solo con buenas decisiones **y** suerte |
| Tier Icon | Excepcional: 1 de cada 20 partidas |

Si el jugador llega a Star siempre, el juego está roto. Si no llega nunca, también.

## 8. Ideas aparcadas para este sistema

- Dificultad seleccionable que ajuste la generosidad del azar.
- Modo "sin suerte" (determinista) para depurar y para tests.
- Simulación de artistas rivales que compiten por las mismas oportunidades y charts.
- Registro de todas las tiradas de una partida para análisis de balance.
