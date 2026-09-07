# 06 — Industria: sellos, contratos, bandas y personas

Sistemas de fase media-tardía. Aquí viven los trade-offs más duros del juego.

---

## 1. Discográficas

| Tipo | Cuándo se acercan | Anticipo | Royalties | Control creativo que te dejan | Presupuesto |
|---|---|---|---|---|---|
| **Autoedición** | Siempre disponible | 0 € | 100% (menos distribución) | 100 | Tu bolsillo |
| **Indie pequeño** | Emerging (5.000+) | 2.000 – 15.000 € | 40 – 50% | 85 – 95 | 3.000 – 15.000 € |
| **Indie grande** | 30.000+ | 15.000 – 120.000 € | 25 – 35% | 60 – 80 | 20.000 – 100.000 € |
| **Subsidiaria de major** | 150.000+ | 100.000 – 600.000 € | 15 – 22% | 40 – 60 | 100.000 – 500.000 € |
| **Major** | 400.000+ | 500.000 – 5.000.000 € | 10 – 18% | 15 – 45 | 500.000 – 3.000.000 € |

**Punto clave**: el anticipo es un **préstamo recuperable**. El sello lo descuenta de tus royalties
antes de pagarte un euro. Muchos artistas con discos de éxito no ven dinero durante años. El juego
debe enseñar esto sin explicarlo con un tutorial: simplemente pasa.

## 2. Anatomía de un contrato

| Cláusula | Rango | Qué significa |
|---|---|---|
| `advance` | 0 – 5.000.000 € | Dinero por adelantado, recuperable contra royalties |
| `albumsRequired` | 1 – 8 | Discos que debes entregar |
| `durationYears` | 1 – 10 | Plazo. Si no cumples, penalización o prórroga forzosa |
| `royaltyRate` | 10 – 50% | Tu porcentaje tras recuperar el anticipo |
| `creativeControl` | 0 – 100 | Cuánto decides tú sobre sonido, singles, portada y fechas |
| `productionBudget` | por disco | Sube el techo de `productionQuality` |
| `promotionBudget` | por lanzamiento | Multiplica el alcance |
| `exclusivity` | sí/no | Prohíbe colaboraciones y publicar por tu cuenta |
| `salesTarget` | número | Objetivo mínimo; fallarlo activa penalizaciones o rescisión |
| `penalties` | € o discos extra | Consecuencia de incumplir |
| `merchCut` / `tourCut` | 0 – 30% | Contratos "360": el sello se lleva parte de tu merch y tus giras |

### 2.1. El dilema central

El contrato que el juego debe ofrecer alrededor de Established:

> **6 discos en 6 años, 1.200.000 € de anticipo, 14% de royalties, control creativo 30.**

Aceptar: dinero inmediato, producción de lujo, promoción masiva, salto de tier casi garantizado. Pero
un disco al año obliga a componer con prisa (`factorTiempoInvertido` bajo), sube el estrés y hunde la
credibilidad si la calidad cae.

Rechazar: libertad total, crecimiento lento, y ver cómo otro artista firma ese contrato y te adelanta.

**Ninguna de las dos debe ser la respuesta correcta.**

### 2.2. Negociación

Las cláusulas son negociables según `businessSense`, `networking`, tu tier, si tienes manager y si
hay más sellos interesados (una puja mejora todo). Un `businessSense` bajo hace que **no veas** las
cláusulas abusivas: el juego te las muestra difuminadas o directamente no te avisa.

## 3. Manager

| Aspecto | Decisión |
|---|---|
| Comisión | 10 – 20% de todos tus ingresos |
| Qué aporta | Mejores ofertas, negocia por ti, filtra oportunidades malas, reduce estrés de gestión, desbloquea contactos |
| Calidad | 0-100: un mal manager cobra igual y consigue peores condiciones |
| Cuándo aparece | A partir de Emerging, o buscándolo activamente con `networking` |
| Riesgo | Managers deshonestos que se quedan dinero (evento descubrible con `businessSense`) |

## 4. Productores

Contratables por proyecto. Coste 500 € – 200.000 € según nivel.

Aportan: techo alto de `productionQuality`, bonus según afinidad con el género, y a veces mejoran la
propia composición. Un productor estrella puede transformar un disco mediocre en uno bueno, pero
imprime **su** sonido: sube `popularity` y puede bajar `artisticCredibility` si es demasiado
reconocible.

## 5. Bandas

### 5.1. Miembros

| Campo | Rango | Nota |
|---|---|---|
| Habilidad instrumental | 0 – 100 | Contribuye a la calidad del directo y de la grabación |
| `ego` | 0 – 100 | Alto: quiere protagonismo, créditos y decisiones |
| `reliability` | 0 – 100 | Bajo: falla a ensayos, llega tarde, riesgo de desastre en directo |
| `ambition` | 0 – 100 | Alto: presiona por crecer; si la banda se estanca, se va |
| `relationship` | 0 – 100 | Relación contigo. Baja con conflictos y reparto injusto |

### 5.2. Dinámica

- **Reparto de ingresos**: a partes iguales, por contribución o desigual a tu favor. La opción egoísta
  da más dinero y erosiona `relationship`.
- **Créditos de composición**: quién firma las canciones. Fuente principal de conflictos.
- **Decisiones creativas**: si tu `preferredGenre` choca con el de la banda, hay tensión.
- **Ruptura**: si la relación media baja de 30, riesgo creciente de disolución. Al separarte, la
  audiencia se reparte: te llevas entre el 30% y el 70% según quién era la cara visible.

### 5.3. Ventajas de tocar en banda
Mejor calidad de directo (más instrumentos), acceso a salas mayores antes, reparto de costes, y
canciones más ricas. A cambio: menos control, menos dinero por cabeza y conflictos.

## 6. Colaboraciones

Conseguir una colaboración depende de: diferencia de tier (los mucho más grandes te ignoran),
`networking`, `industryReputation`, afinidad de género, relación previa y sello común.

Efecto: la canción resultante mezcla las estadísticas de ambos y accede a la audiencia del otro
artista. Un featuring con alguien dos tiers por encima es el acelerador más potente del juego, y por
eso debe ser difícil de conseguir.

## 7. Relaciones entre artistas

Red de contactos con valor 0-100 por persona. Se construye con `networking`, giras compartidas,
colaboraciones y eventos. Se destruye con conflictos, robar miembros de banda, polémicas públicas.

Los contactos abren puertas que el dinero no: teloneos, sesiones, recomendaciones a sellos.

## 8. Ideas aparcadas para este sistema

- Abogados y auditorías de contratos (descubrir que llevas años cobrando de menos).
- Recuperar los derechos de tu catálogo antiguo.
- Sellos propios: fichar y producir a otros artistas.
- Rivalidades entre artistas con efecto en prensa y ventas.
- Editoriales musicales y royalties de composición separados de los de grabación.
