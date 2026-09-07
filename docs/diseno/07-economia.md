# 07 — Economía, equipamiento y posesiones

Moneda: **euros (€)**. Dinero inicial: **150 €**. Deuda máxima: **−2.000 €** (a partir de ahí,
evento de ejecución: te quitan equipo).

---

## 1. Gastos fijos mensuales

Se cobran el día 1 de cada mes. Si no hay dinero, sube el estrés y aparecen eventos de presión.

| Concepto | Coste | Nota |
|---|---|---|
| Habitación compartida | 250 €/mes | Vivienda inicial |
| Comida y básicos | 150 €/mes | Obligatorio |
| Transporte | 40 €/mes | |
| Cuerdas, parches, mantenimiento | 20 €/mes | Escala con la cantidad de equipo |
| Internet/móvil | 25 €/mes | Necesario para redes |

**Suelo de supervivencia: ~485 €/mes.** Una sesión de calle inicial da 5 €. El mensaje de diseño es
inmediato: al principio **no puedes vivir de la música**, y tendrás que trabajar o pedir ayuda.

## 2. Fuentes de ingresos

| Fuente | Cuándo | Rango |
|---|---|---|
| Propinas de calle | Desde el día 1 | 3 – 60 € por sesión |
| Trabajo a tiempo parcial | Siempre | 45 € por franja |
| Bolos en bares | Local Act | 50 – 200 € |
| Conciertos | Emerging+ | 200 € – 500.000 € |
| Trabajo de sesión | Habilidad ≥ 60 | 80 – 400 € por sesión |
| Clases de música a otros | Habilidad ≥ 55 | 25 €/hora |
| Streams | Tras publicar | **0,003 €/reproducción** |
| Ventas físicas/digitales | Tras publicar | 3 – 12 € por unidad, menos coste de fabricación |
| Merchandising | Local Act+ | Margen 8 – 20 € por artículo |
| Anticipos de contrato | Al firmar | 2.000 € – 5.000.000 € |
| Royalties de composición | Si firmas tus temas | ~15% adicional sobre lo generado |
| Licencias (anuncios, series) | Established+ | 5.000 – 500.000 €, evento aleatorio |

**Nota sobre streams**: 0,003 €/reproducción significa que **un millón de reproducciones son 3.000 €**
brutos, y con un sello al 15% te quedan 450 €. Es real y es una lección de diseño deliberada: el
streaming no paga, pagan los directos y el merch.

## 3. Instrumentos y equipo

Cada objeto tiene `quality` (0-100) y `condition` (0-100). La condición baja con el uso
(−0,5 por actuación) y se repara pagando. El multiplicador de calidad efectivo va de **0,85** a
**1,15** según `quality × condition`.

### 3.1. Instrumentos

| Objeto | Gama baja | Gama media | Gama alta | Boutique |
|---|---|---|---|---|
| Guitarra acústica | 120 € | 450 € | 1.800 € | 5.000 € |
| Guitarra eléctrica | 180 € | 700 € | 2.500 € | 8.000 € |
| Bajo | 200 € | 650 € | 2.200 € | 6.000 € |
| Teclado / piano digital | 250 € | 900 € | 3.500 € | 12.000 € |
| Batería | 400 € | 1.400 € | 5.000 € | 15.000 € |

### 3.2. Amplificación y directo

| Objeto | Coste | Efecto |
|---|---|---|
| Amplificador pequeño | 150 € | Necesario para eléctrica en la calle |
| Amplificador de escenario | 700 € | +5% calidad en directo |
| Micrófono decente | 90 € | +8% a las canciones cantadas |
| Equipo de PA portátil | 600 € | Permite tocar en sitios sin equipo |
| Pedales/efectos | 60 – 900 € | Bonus por género |

### 3.3. Grabación

| Nivel | Coste | Techo de `recordingQuality` |
|---|---|---|
| Móvil / grabadora | 0 € | 25 |
| Setup casero básico (interfaz + micro + software) | 500 € | 50 |
| Home studio decente | 2.500 € | 70 |
| Home studio profesional | 12.000 € | 85 |
| Estudio profesional alquilado | 400 €/día | 95 |
| Estudio de referencia | 2.000 €/día | 100 |

### 3.4. Transporte

Sin vehículo, limitado a tu ciudad. Furgoneta de segunda mano 4.000 € → habilita giras pequeñas.
Nightliner de gira: alquiler por gira, decenas de miles.

## 4. Vestuario y estilo

Cada prenda aporta `charismaBonus` (0-10), `credibilityBonus` (−5 a +10) y pertenece a un **estilo**
(rock, pop, indie, urbano, elegante, alternativo).

**Coherencia**: si el estilo dominante del conjunto coincide con el género de la actuación, +10-20%
de conversión de público a fans. Si choca (traje elegante en un concierto punk), −10%.

Rangos de precio: 20 € (mercadillo) a 2.000 € (diseñador). El vestuario caro solo compensa a partir
de audiencias grandes, y es una forma clásica de arruinarse en el juego.

## 5. Vivienda

| Vivienda | Coste | Efecto |
|---|---|---|
| Casa de los padres | 0 € | Recuperación ×1,0; eventos de presión familiar |
| Habitación compartida | 250 €/mes | Recuperación ×0,9; no se puede ensayar de noche |
| Piso pequeño | 600 €/mes | Recuperación ×1,0; permite ensayar |
| Piso bueno | 1.200 €/mes | Recuperación ×1,1; espacio para home studio |
| Casa con estudio | 2.500 €/mes o 350.000 € | Recuperación ×1,2; estudio propio integrado |
| Mansión | 8.000 €/mes+ | Recuperación ×1,2, +credibilidad de "estrella", gasto brutal |

## 6. Merchandising

Desbloqueado en Local Act. Requiere inversión inicial (tirada de camisetas: 500 €).

```
ventasPorConcierto = asistentes × tasaCompra × precioMedio
tasaCompra = 2% + proporciónDeFansFieles × 15%
```

Los fans fieles compran; los casuales, casi nunca. Es el sistema que hace rentable la carrera
underground.

## 7. Filosofía económica del juego

1. **Al principio el dinero es asfixiante** y obliga a decidir entre música y supervivencia.
2. **En el medio, el dinero es inversión**: mejor equipo y mejor producción se pagan solos.
3. **Al final, el dinero deja de ser el problema** y el recurso escaso pasa a ser el tiempo, la
   inspiración y la credibilidad.

## 8. Ideas aparcadas para este sistema

- Impuestos y gestoría a partir de cierto nivel de ingresos.
- Inversiones y ruina (el clásico músico arruinado tras años de éxito).
- Alquilar equipo en vez de comprarlo.
- Seguro de equipo frente a robos.
- Crowdfunding para financiar un disco sin sello.
