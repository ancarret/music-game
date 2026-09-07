# 04 — Directos: de la calle a los estadios

---

## 1. La escalera de los directos

| Nivel | Requisito de acceso | Ingresos típicos | Aforo |
|---|---|---|---|
| **Calle** | Ninguno | 3 – 60 € | Público de paso |
| **Micro abierto** | Ninguno | 0 € (+ consumición) | 20 – 50 |
| **Bar** | 300 seguidores o reputación local 25 | 50 – 200 € | 40 – 120 |
| **Sala pequeña** | 2.000 seguidores | 200 – 1.500 € | 150 – 400 |
| **Sala mediana** | 20.000 seguidores | 1.500 – 12.000 € | 400 – 1.500 |
| **Teloneo de gira** | Contactos o sello | Poco dinero, mucha exposición | Variable |
| **Festival pequeño** | 30.000 seguidores | 3.000 – 20.000 € | 2.000 – 10.000 |
| **Sala grande / pabellón** | 200.000 seguidores | 20.000 – 150.000 € | 2.000 – 15.000 |
| **Festival grande (cabeza de cartel)** | 1.000.000 seguidores | 100.000 – 800.000 € | 30.000+ |
| **Estadio** | Tier Icon | 500.000 € + | 40.000+ |

Subir de nivel no es solo tener seguidores: hay que **llenar**. Tocar en una sala demasiado grande y
dejarla medio vacía daña la reputación de industria y el caché futuro.

---

## 2. La calle (sistema completo)

El primer sistema económico del juego y el que se implementa primero.

### 2.1. Ubicaciones

| Ubicación | Tráfico | Riqueza | Requisito | Nota |
|---|---|---|---|---|
| Barrio residencial | 30 | 40 | — | Tranquilo, poca gente, poca competencia |
| Plaza del mercado | 70 | 45 | — | El punto de partida estándar |
| Zona universitaria | 65 | 30 | — | Poco dinero, pero mucha conversión a seguidores |
| Calle comercial | 85 | 60 | Reputación local 15 | Competencia con otros músicos |
| Zona turística | 90 | 75 | Reputación local 30 | Máximo potencial de propinas |
| Metro | 100 | 35 | Permiso municipal (80 €, examen) | Volumen brutal, público con prisa |

### 2.2. Cálculo de una actuación callejera

```
1. transeúntes = tráficoUbicación × factorFranja × factorDíaSemana × factorClima × factorEstación

2. interpretación = (ver 03-musica.md, sección 4)

3. tasaDeParada = base(2%)
                + interpretación × 0.18 %
                + popularidadCanción × 0.06 %
                + charisma × 0.04 %
                + reputaciónLocal × 0.05 %
                (máximo 25%)

4. público = transeúntes × tasaDeParada

5. tasaDePropina = 5% + interpretación × 0.3%          (máximo 40%)
   propinaMedia  = 0.4 € + riquezaUbicación × 0.03 €    (0,4 € – 2,7 €)
   dinero = público × tasaDePropina × propinaMedia × aleatorio(0.8 … 1.3)

6. seguidoresNuevos = público × tasaConversión
   tasaConversión = (interpretación − 40) × 0.08%       (0% si interpretación < 40, máx 8%)

7. reputaciónLocal += interpretación × 0.02
```

| Modificador | Valores |
|---|---|
| `factorFranja` | mañana 0,7 · tarde 1,0 · noche 1,2 |
| `factorDíaSemana` | laborable 1,0 · viernes 1,2 · sábado 1,4 · domingo 1,1 |
| `factorClima` | soleado 1,2 · nublado 1,0 · frío 0,8 · lluvia 0,3 |
| `factorEstación` | verano 1,2 · Navidad 1,3 · resto 1,0 |

### 2.3. Ejemplo de balance (personaje inicial)

Guitarra 40, performance 14, carisma 45, plaza del mercado, sábado tarde, soleado, tocando un cover
muy conocido que domina: interpretación ≈ 42 → público ≈ 8 personas → **≈ 5 €** y 0-1 seguidores.

Ese es el suelo deliberado: la calle al principio da para comer, no para vivir. Con guitarra 70 y
performance 50 en zona turística, la misma sesión ronda los 45-60 €.

### 2.4. Eventos de calle

Tirada tras cada actuación:

| Evento | Probabilidad | Efecto |
|---|---|---|
| Alguien graba y sube el vídeo | 3% (×3 si interpretación > 75) | Tirada de viralidad |
| Ojeador / dueño de bar | 2% si interpretación > 70 | Oferta de bolo |
| Otro músico propone tocar juntos | 2% | Contacto, posible banda |
| Policía / multa | 4% sin permiso | −50 €, fin de la sesión |
| Lluvia repentina | según estación | Sesión cortada, ingresos ×0,4 |
| Cliente generoso | 1,5% | +20 a +200 € de golpe |
| Competencia por el sitio | 5% en ubicaciones buenas | Toca elegir: ceder, negociar o plantarse |

---

## 3. Conciertos

### 3.1. Antes: preparación
- **Setlist**: elegir canciones y orden. Importa la curva de energía (`energyLevel` de cada canción):
  arrancar fuerte, bajar en el centro, cerrar arriba. Un setlist bien construido da hasta +12% a la
  calidad de la actuación.
- **Ensayos previos**: cada sesión sube familiaridad del repertorio elegido.
- **Estado**: energía y estrés al llegar al concierto pesan mucho.

### 3.2. Durante: cálculo
```
calidadConcierto = media ponderada de la interpretación de cada canción del setlist
                 × factorSetlist
                 × factorPúblico       (¿te conocen? ¿es tu género?)
                 × factorEquipoYSala
                 × factorBanda         (si tocas acompañado)
                 × aleatorio(0.9 … 1.1)
```

**Minijuegos de ritmo**: en 2-4 momentos concretos del concierto aparece una indicación de pulsar una
tecla sincronizada. Acertar todas da **+5%** a la calidad final; fallar todas, **−5%**. Deliberadamente
poco: es sabor, no el sistema. En el prototipo de consola no existen; se resuelven automáticamente
con la habilidad `performance`.

### 3.3. Después: resultados
Ingresos (caché fijo + porcentaje de taquilla + merch), seguidores nuevos, conversión de casuales a
fieles, credibilidad, reseñas de prensa a partir de sala mediana, y energía/estrés consumidos.

Un concierto excepcional (calidad > 85) puede generar un **momento memorable**: entra en el historial
y da bonus permanente de reputación en esa ciudad.

## 4. Giras

Secuencia de conciertos en varias ciudades durante semanas o meses.

Decisiones: qué ciudades, cuántas fechas, tamaño de las salas, con qué banda, con qué producción,
precio de las entradas.

Sistemas propios: coste logístico (transporte, hoteles, equipo, personal), desgaste (+5 estrés/día,
recuperación reducida), riesgo de cancelación por salud, y crecimiento de audiencia por ciudad
visitada.

Una gira mal dimensionada **arruina**: salas grandes sin llenar, con costes fijos altísimos.

## 5. Ideas aparcadas para este sistema

- Público segmentado por ciudad: llenar en Madrid y no vender entradas en Bilbao.
- Teloneros propios y elegir a quién das esa oportunidad.
- Incidentes en directo: cortes de luz, invasión de escenario, cuerdas rotas a mitad de canción.
- Residencias en una ciudad (varias noches en la misma sala).
- Conciertos benéficos y su efecto en credibilidad.
