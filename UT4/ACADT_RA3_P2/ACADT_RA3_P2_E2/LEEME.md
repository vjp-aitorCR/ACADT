# ACADT_RA3_P2_E2 · Laboratorio de estados de una entidad

Proyecto creado a partir de `plantilla-orm-02`. Base de datos: `ra3_p2_e2` (se crea sola).

## Puesta en marcha

1. Importar como proyecto Maven (*File → Import → Maven → Existing Maven Projects*).
2. Comprobar usuario y contraseña de MySQL en `src/main/resources/jpa.properties`.
3. Ejecutar `LaboratorioEstados.java` como aplicación Java.

## Estructura

```
modelo/Envio.java            Entidad del ejercicio 1
modelo/Direccion.java        @Embeddable de la plantilla, sin cambios
util/JPAUtil.java            Registra Envio y Direccion con .managedClass()
LaboratorioEstados.java      Un método por paso (1 a 9), tras guardar tres envíos de ejemplo
```

## Qué envío usa cada paso

| Paso | Envío | Qué se observa |
| --- | --- | --- |
| 1 | nuevo (id 4) | `contains` pasa de `false` a `true`; con IDENTITY el id aparece en `persist()` |
| 2 | 1 | Una sola SELECT y `a == b` es `true` |
| 3 | 2 | Se guarda el peso (con transacción) pero no la descripción (sin transacción) |
| 4 | 3 | Al cerrar el EntityManager el objeto es *detached*: el destino no cambia en la BD |
| 5 | 1 | Tras `detach()` el cambio de peso no se guarda |
| 6 | 3 (del paso 4) | `merge()` devuelve otro objeto; solo se guarda lo que había en el momento del `merge()` |
| 7 | 2 | `contains` es `false` antes del commit y `find()` devuelve `null` después |
| 8 | 1 y id 999 | Excepciones de `remove()`/`persist()` sobre detached y de `getReference()`; `find(999)` da `null` |
| 9 | nuevo (id 5) + código repetido | Rollback: el id queda en memoria, pero el envío no está en la BD |

La tabla de estados y SQL de cada paso está en el documento adjunto.
