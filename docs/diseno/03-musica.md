# 03 — Música: canciones, creación y lanzamientos

La entidad más importante del dominio después del personaje.

---

## 1. Dos tipos de canción

| | **Cover** | **Canción propia** |
|---|---|---|
| Origen | Dataset de canciones reales (ver `09-datos.md`) | La crea el jugador |
| Sirve para | Tocar en la calle y en directo, atraer público | Todo lo anterior + publicar, generar ingresos pasivos y construir carrera |
| Estado que guarda | Familiaridad del personaje con ella | Ciclo de vida completo |
| Ingresos | Solo del directo (propinas, caché) | Directo + streams + ventas + licencias |

Un cover **no genera royalties**: es la forma de ganar público sin obra propia. En etapas avanzadas,
tocar demasiados covers penaliza la credibilidad artística.

## 2. Datos de una canción (catálogo)

Válido para covers y, en parte, para canciones propias.

| Campo | Rango | Nota |
|---|---|---|
| `title` | texto | |
| `originalArtist` | referencia a artista | |
| `genre` | referencia a género | |
| `releaseYear` | año | Las canciones antiguas y muy conocidas funcionan mejor en la calle |
| `popularity` | 0 – 100 | Cuánto la reconoce el público general |
| `difficulty` | 0 – 100 | Habilidad necesaria para interpretarla dignamente |
| `durationSeconds` | 60 – 600 | Afecta a cuántas caben en un set |
| `energyLevel` | 0 – 100 | Canción tranquila vs. explosiva. Importa para el orden del setlist |

## 3. Familiaridad con una canción

Rango **0 – 100**. Cada canción del repertorio del personaje tiene la suya.

- Al aprender un cover, empieza en: `40 − dificultad/3 + habilidadInstrumento/4` (mínimo 5).
- Sube ensayando: `+8 a +15` por sesión, con rendimientos decrecientes.
- Baja sola: **−1 por semana sin tocarla** (por debajo de 40 no baja más).

Es el sistema que hace que un repertorio grande y descuidado sea peor que uno pequeño y dominado.

## 4. Calidad de interpretación

Se calcula **cada vez** que se toca una canción:

```
interpretación = 0.40 × habilidadInstrumento
               + 0.25 × familiaridad
               + 0.20 × performance
               + 0.15 × musicality
               − penalizaciónDificultad
               × factorEnergía × factorEstrés × factorEquipo
               × aleatorio(0.90 … 1.10)
```

`penalizaciónDificultad = max(0, (dificultad − habilidadInstrumento) × 0.5)` → tocar algo muy por
encima de tu nivel destroza la interpretación aunque te la sepas.

`factorEquipo`: 0,85 (instrumento cascado) a 1,15 (equipo excelente).

## 5. Ciclo de vida de una canción propia

```
IDEA → BORRADOR → COMPUESTA → ENSAYADA → GRABADA → PRODUCIDA → PUBLICADA
```

| Estado | Cómo se llega | Qué fija |
|---|---|---|
| **Idea** | 1 sesión de composición | Género, título provisional, `potential` oculto |
| **Borrador** | 2-4 sesiones más | Estructura; ya se puede tocar en directo (en versión cruda) |
| **Compuesta** | Sesiones de pulido | `compositionQuality` definitiva |
| **Ensayada** | Sesiones de ensayo | Familiaridad, necesaria para grabarla bien |
| **Grabada** | Acción de grabar | `recordingQuality` |
| **Producida** | Acción de producir/mezclar | `productionQuality` |
| **Publicada** | Lanzamiento | Empieza a generar métricas e ingresos |

Una canción puede **quedarse guardada** en cualquier estado y retomarse años después. Publicar una
canción vieja compuesta en la etapa underground puede ser una gran jugada narrativa.

### 5.1. Calidad de composición

```
compositionQuality = (0.40 × songwriting + 0.35 × creativity + 0.25 × musicality)
                   × factorInspiración
                   × factorTiempoInvertido
                   × factorGénero
                   × aleatorio(0.85 … 1.20)
```

| Factor | Valor |
|---|---|
| `factorInspiración` | inspiración > 70 → 1,15 · 20-70 → 1,00 · < 20 → 0,60 |
| `factorTiempoInvertido` | 1 sesión → 0,70 · 3 → 0,90 · 6 → 1,00 · 12 → 1,10 · 20+ → 1,15 (techo claro: currar más no compensa infinitamente) |
| `factorGénero` | 1,10 si es el `preferredGenre`, 0,95 si es un género con habilidad baja |

### 5.2. Potencial oculto (`potential`)

Cada canción propia recibe al nacer un `potential` de **0 a 100** que el jugador **nunca ve**. Es la
suerte estructural de esa canción: si tiene ese "algo". Se genera con media 40, pero `creativity`
alta desplaza la distribución hacia arriba y da opción a valores de 90+.

Es lo que permite que una canción compuesta en una tarde tonta se convierta en el mayor éxito de la
carrera. Ver `08-simulacion.md`.

### 5.3. Calidad final

```
finalQuality = 0.45 × compositionQuality
             + 0.25 × recordingQuality
             + 0.30 × productionQuality
```

`recordingQuality` depende de: interpretación media en la toma, calidad del estudio, número de tomas
(sesiones invertidas). `productionQuality` depende de: `production` propia o del productor
contratado, presupuesto y equipo.

**Consecuencia de diseño buscada:** una gran composición mal grabada se queda en 60. Una composición
mediocre con producción de lujo tampoco llega a 80. Hacen falta las tres cosas.

## 6. Géneros y tendencias

Géneros iniciales: rock · pop · indie · folk · jazz · blues · metal · punk · electrónica · hip-hop ·
R&B · clásica · reguetón · flamenco.

Cada género tiene:

| Propiedad | Qué hace |
|---|---|
| `mainstreamAppeal` (0-100) | Techo de audiencia potencial |
| `credibilityWeight` (0-100) | Cuánta credibilidad artística aporta el éxito en él |
| `trend` (0-200, base 100) | Momento de mercado. Se mueve ±15 por trimestre |
| `loyaltyFactor` | Cómo de fieles son sus fans (metal y folk altos; pop bajo) |

Las tendencias se mueven solas y por eventos culturales. Publicar en un género en auge multiplica el
alcance ×1,5; en un género hundido, ×0,6. **Perseguir la tendencia** es una estrategia válida, pero
cuesta credibilidad y llega tarde si tardas un año en sacar el disco.

## 7. Lanzamientos

| Formato | Canciones | Cuándo tiene sentido |
|---|---|---|
| **Single** | 1 (+ cara B opcional) | Mantener presencia, probar un sonido, preparar terreno |
| **EP** | 3 – 6 | Primer paquete serio, típico de Local Act / Emerging |
| **Álbum** | 8 – 16 | Declaración artística. Motor de giras y prensa |
| **Recopilatorio / En directo** | variable | Etapas tardías, cumplir contrato con poco esfuerzo (y coste de credibilidad) |

Decisiones en cada lanzamiento: qué canciones, en qué orden, con qué presupuesto de promoción, en qué
momento del año, con o sin sello.

### 7.1. Rendimiento comercial

```
alcanceEsperado = audienciaBase × factorCalidad × factorPromoción × factorTendencia
                × factorTiming × factorSello
alcanceReal     = alcanceEsperado × loteríaDeÉxito(potential)
```

`loteríaDeÉxito` es la clave del principio "esfuerzo + incertidumbre": normalmente devuelve 0,6-1,4,
pero con probabilidad pequeña (creciente con `potential` y `finalQuality`) devuelve entre 3× y 50×.
Detalle en `08-simulacion.md`.

De ahí salen: reproducciones, ventas, posición en charts, ingresos, seguidores ganados y variación de
credibilidad/popularidad.

### 7.2. Cola larga

Una canción publicada sigue generando reproducciones decrecientes durante meses (−15% mensual), y una
canción antigua puede **resucitar** por un evento (aparece en una serie, se vuelve meme, la versiona
alguien famoso).

## 8. Ideas aparcadas para este sistema

- Colaboraciones que mezclan las estadísticas de dos artistas en una canción.
- Versiones: acústica, remix, en directo, regrabación ("Taylor's Version").
- Letras con temática que afecte a qué segmento de fans conecta.
- Canciones descartadas que se filtran y generan hype o vergüenza.
- Créditos de composición compartidos y su reparto de royalties.
