# 02 — Tiempo, energía y acciones

El motor del juego. Todo lo demás cuelga de aquí.

---

## 1. Calendario

| Unidad | Decisión |
|---|---|
| Turno | **1 día** |
| Franjas por día | **3**: mañana, tarde, noche |
| Semana | 7 días. Fines de semana: más público en la calle y en locales |
| Mes | 30 días |
| Año | 12 meses = 360 días (simplificación deliberada) |
| Inicio de partida | 1 de enero, 16 años |

**Estaciones**: cada 3 meses. Afectan al clima, y el clima afecta a la calle (ver `04-directos.md`).
Diciembre y verano tienen más turismo y festivales.

## 2. Franjas y acciones

Cada acción ocupa **1 franja** salvo que se indique lo contrario. El jugador decide qué hace en cada
una de las tres, o descansa.

**Regla de la noche**: si se deja la franja de noche libre, se duerme bien y se recupera energía
completa. Usar la noche para actuar o trabajar da ingresos pero recupera solo el 60% de la energía y
suma estrés.

## 3. Energía

`energyMax = 80 + stamina/5` → entre 80 y 100.

Recuperación al dormir: `+45 + stamina/4`, ajustada por vivienda (×0,9 a ×1,2) y estrés
(>70 → ×0,8).

| Nivel de energía | Efecto |
|---|---|
| > 70 | +10% de rendimiento en todo |
| 30 – 70 | Normal |
| < 30 | −40% de rendimiento, riesgo de errores en directo |
| 0 | Colapso: se pierde el resto del día y +15 estrés |

## 4. Estrés

Sube: encadenar 3+ franjas ocupadas al día (+4/día), deadlines de contrato próximos (+3/día),
giras (+5/día), fracasos sonados (+10), problemas de dinero (+5/mes si el balance es negativo).

Baja: descansar una franja (−5), un día entero libre (−15), vacaciones (−30), éxitos (−10).

Efectos por encima de 70: −35% aprendizaje, −20% calidad creativa, −20% recuperación de energía.
Por encima de 85: 8% de probabilidad diaria de evento de burnout (pierde 3 días, +1 rasgo *Quemado*).

## 5. Inspiración

Necesaria para componer. Se consume al componer (−25 por sesión de composición seria).

Se recupera: descansar (+5), escuchar música (+10, 1 franja), pasear/viajar (+12), conciertos de
otros (+15), eventos vitales (+10 a +40), enamorarse o romper (+30, evento).

Componer con inspiración < 20 es posible, pero la calidad de composición se multiplica por 0,6.

## 6. Catálogo de acciones

Costes y efectos concretos. `n` = nivel de la habilidad relevante.

### 6.1. Formación

| Acción | Energía | Efecto | Requisitos |
|---|---|---|---|
| **Practicar instrumento** | 15 | Sube la habilidad del instrumento según la fórmula de `01-personaje.md` | Poseer el instrumento |
| **Ensayar una canción** | 12 | +8 a +15 de familiaridad con esa canción concreta | Tenerla en el repertorio |
| **Estudiar teoría musical** | 18 | +0,8 `songwriting`, +0,5 a todos los instrumentos | — |
| **Clases particulares** | 12 | Ganancia de habilidad ×1,8 esa sesión | 40 €/sesión |
| **Aprender producción** | 18 | +1,2 `production` | Tener software/equipo básico |
| **Trastear en redes** | 8 | +0,6 `marketing`, +10 a 200 seguidores según audiencia actual | — |

### 6.2. Creación

| Acción | Energía | Efecto |
|---|---|---|
| **Componer** | 20 | Avanza una canción propia. Consume 25 de inspiración. Ver `03-musica.md` |
| **Pulir una canción** | 15 | +3 a +8 calidad de composición, con techo por `songwriting` y rendimientos decrecientes |
| **Grabar** (2 franjas) | 30 | Fija la calidad de grabación. Coste variable según estudio |
| **Producir/mezclar** | 20 | Fija la calidad de producción |

### 6.3. Ingresos y directo

| Acción | Energía | Efecto |
|---|---|---|
| **Tocar en la calle** | 22 | Ver `04-directos.md`. Dinero + seguidores + reputación local |
| **Concierto** (2 franjas) | 35 | Caché, fans, credibilidad, estrés |
| **Trabajo a tiempo parcial** | 25 | 45 € por franja. Sin progreso musical. La opción "aburrida" que a veces salva la partida |
| **Trabajo de sesión** | 25 | 80-400 € según habilidad instrumental y reputación. Requiere habilidad ≥ 60 y contactos |

### 6.4. Industria y social

| Acción | Energía | Efecto |
|---|---|---|
| **Hacer contactos** | 15 | +0,8 `networking`, probabilidad de desbloquear oportunidad |
| **Reunión** (sello, manager, banda) | 10 | Avanza una negociación concreta |
| **Promocionar un lanzamiento** | 15 | Aumenta el alcance del lanzamiento activo |
| **Ir a un concierto** | 10 | +15 inspiración, posibilidad de contacto |

### 6.5. Descanso

| Acción | Energía | Efecto |
|---|---|---|
| **Descansar** | — | −5 estrés, +5 inspiración |
| **Día libre completo** | — | −15 estrés, +12 inspiración, recuperación total |
| **Vacaciones** (7 días) | — | −30 estrés, +40 inspiración. Cuesta dinero y audiencia (decay) |

## 7. Eventos aleatorios

Al final de cada día, una tirada decide si ocurre un evento. Probabilidad base **12% diaria**,
modificada por contexto (más alta en gira, tras un viral, con estrés alto).

Categorías, con ejemplos ya decididos:

| Categoría | Ejemplos |
|---|---|
| **Oportunidad** | Un local te ofrece tocar el sábado · Un artista local propone colaborar · Alguien sube tu actuación callejera y funciona |
| **Contratiempo** | Se te rompe una cuerda/plato/tecla (coste de reparación) · Te roban el instrumento · Cancelación de un bolo · Enfermedad (2 días) |
| **Industria** | Un manager se interesa · Un sello pide una reunión · Un productor te propone algo |
| **Personal** | Tus padres presionan para que estudies · Un amigo te ofrece piso más barato · Ruptura o nueva relación |
| **Cultural** | Cambia la tendencia de un género · Muere un artista de referencia (sube el interés por su estilo) · Aparece un movimiento nuevo |

Los eventos **nunca** deben ser solo texto: cada uno modifica estado o presenta una decisión con
consecuencias.

## 8. Ideas aparcadas para este sistema

- Agenda con compromisos a fecha fija (bolos firmados, entrevistas, fechas de entrega de disco).
- Estaciones con efectos económicos fuertes (temporada de festivales, campaña de Navidad).
- Acciones de más de un día que ocupan el calendario (grabación de álbum, gira).
- Rutinas guardadas: repetir automáticamente una semana tipo para no microgestionar en fases tardías.
