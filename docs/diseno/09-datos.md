# 09 — Datos: artistas y canciones reales

Cómo se alimenta el juego con música real durante el prototipo privado.

---

## 1. Regla de acoplamiento

Los datos reales son **filas**, nunca lógica. El dominio (`Artist`, `Song`, `Genre`) no sabe que
existe Guns N' Roses: sabe que existe un artista con cierta popularidad y cierto género.

Prueba de fuego del diseño: **cambiar el dataset entero por nombres inventados no debe requerir tocar
una línea de lógica**. Si algún día hay que hacerlo por derechos de autor, es un cambio de datos.

## 2. Esquema de datos

### 2.1. Artista

| Campo | Tipo | Notas |
|---|---|---|
| `id` | identificador | Estable, no el nombre |
| `name` | texto | |
| `primaryGenre` | referencia a género | |
| `era` | década o rango de años | Para eventos culturales y nostalgia |
| `fameTier` | 1 – 5 | Local, conocido, grande, superestrella, leyenda |
| `country` | texto | Para audiencia regional futura |
| `active` | booleano | Si sigue en activo dentro del universo del juego |

### 2.2. Canción

| Campo | Tipo | Notas |
|---|---|---|
| `id` | identificador | |
| `title` | texto | |
| `artistId` | referencia | |
| `genre` | referencia a género | |
| `releaseYear` | año | |
| `popularity` | 0 – 100 | Reconocimiento del público general **hoy** |
| `difficulty` | 0 – 100 | Dificultad de interpretación |
| `durationSeconds` | entero | |
| `energyLevel` | 0 – 100 | Para construir setlists |
| `streetFriendly` | booleano | Si funciona tocada en la calle a voz e instrumento |

`popularity` y `difficulty` son **valoraciones de diseño**, no datos objetivos existentes: hay que
asignarlas nosotros con criterio.

## 3. Estrategia de obtención (decisión tomada)

Se descarta generar 20.000 filas de memoria: los títulos, años y autorías saldrían con errores, y un
dataset sucio contamina todo el balanceo del juego.

Plan en tres etapas:

| Etapa | Qué | Tamaño | Cuándo |
|---|---|---|---|
| **1. Semilla de calle** | Covers clásicos que funcionan tocados en la calle, curados a mano con metadatos verificados | 40 – 60 canciones | Cuando se implemente el repertorio (capa 6 del personaje) |
| **2. Catálogo jugable** | Artistas y canciones suficientes para cubrir todos los géneros y décadas | ~200 artistas, ~1.500 canciones | Al llegar a la fase de datos externos (fichero JSON/CSV) |
| **3. Catálogo masivo** | Importación desde una fuente real | 1.000+ artistas, 20.000+ canciones | Al llegar a PostgreSQL (Fase 7) |

### 3.1. Fuentes para la etapa 3

| Fuente | Ventajas | Inconvenientes |
|---|---|---|
| **MusicBrainz** (volcado público) | Gratis, enorme, sin límites de API, datos abiertos | No trae popularidad; hay que derivarla |
| **Spotify Web API** | Trae popularidad real (0-100), géneros, duración | Requiere credenciales y respetar límites de uso |
| **Datasets públicos (Kaggle y similares)** | Ya vienen limpios y con métricas de audio | Calidad variable, pueden estar desactualizados |

Recomendación: **MusicBrainz para el catálogo + Spotify para la popularidad**, y la dificultad
asignada por reglas propias (número de acordes, tesitura vocal, tempo, técnica requerida) más ajuste
manual en las canciones importantes.

**Importante:** importar un dataset grande es un ejercicio de backend excelente (lectura de ficheros,
parseo, validación, carga masiva a base de datos, deduplicación), así que se hará **como ejercicio de
la fase que toque**, no como un regalo hecho de antemano.

## 4. Criterios para asignar `difficulty`

Escala de referencia para mantener coherencia al puntuar:

| Rango | Perfil | Ejemplo de tipo de canción |
|---|---|---|
| 0 – 20 | Tres o cuatro acordes, ritmo estable, tesitura cómoda | Clásicos de hoguera |
| 21 – 40 | Cambios sencillos, algo de dinámica | Pop y rock estándar |
| 41 – 60 | Cejillas, ritmos sincopados, rango vocal amplio | Rock clásico exigente |
| 61 – 80 | Solos, técnica específica, resistencia | Rock progresivo, funk, jazz accesible |
| 81 – 100 | Virtuosismo, velocidad extrema, armonía compleja | Metal técnico, jazz complejo, clásica de concierto |

## 5. Criterios para `popularity`

Reconocimiento **actual** del público general, no ventas históricas:

| Rango | Significado |
|---|---|
| 90 – 100 | La reconoce prácticamente todo el mundo en dos segundos |
| 70 – 89 | Muy conocida entre varias generaciones |
| 50 – 69 | Conocida por el público de su género o su época |
| 30 – 49 | La reconocen aficionados |
| 0 – 29 | De nicho o profundamente de catálogo |

En la calle, `popularity` alta sube la tasa de parada; pero un repertorio solo de éxitos obvios
penaliza la credibilidad artística en etapas avanzadas.

## 6. Uso legal

Prototipo **privado y educativo**: se usan nombres de artistas y títulos como metadatos, nunca
letras, audio ni imágenes con derechos. Si el juego llegara a publicarse, el dataset se sustituye por
contenido ficticio o licenciado — y por eso el punto 1 de este documento es innegociable.

## 7. Ideas aparcadas para este sistema

- Artistas reales como NPCs activos: compiten en charts, ofrecen colaboraciones, van de gira.
- Línea temporal histórica: empezar en los 80 y ver evolucionar la industria.
- Generador de artistas ficticios para rellenar el mundo sin usar datos reales.
- Derivar `difficulty` automáticamente a partir de características de audio.
