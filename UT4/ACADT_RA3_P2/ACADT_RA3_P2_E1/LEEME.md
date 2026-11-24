# ACADT_RA3_P2_E1 · Envíos con direcciones embebidas

Proyecto creado a partir de `plantilla-orm-02`. Base de datos: `ra3_p2_e1` (se crea sola).

## Puesta en marcha

1. Importar como proyecto Maven (*File → Import → Maven → Existing Maven Projects*).
2. Comprobar usuario y contraseña de MySQL en `src/main/resources/jpa.properties`.
3. Ejecutar `AltaEnvios.java` como aplicación Java.

## Estructura

```
modelo/Envio.java       Entidad (tabla envios) con dos @Embedded + @AttributeOverride
modelo/Direccion.java   @Embeddable de la plantilla, sin cambios
util/JPAUtil.java       Registra Envio y Direccion con .managedClass()
AltaEnvios.java         persist() de tres envíos en una transacción y find() de cada uno
```

## Prueba pedida: quitar las @AttributeOverride

Si se eliminan las `@AttributeOverride` de `origen` y de `destino`, las dos direcciones intentan
usar las mismas columnas (`via`, `codigoPostal`, `poblacion`, `provincia`). Hibernate lo detecta al
arrancar (al crear el `EntityManagerFactory` en `JPAUtil`) y lanza un error de mapeo del tipo
*"Column 'via' is duplicated in mapping for entity ... Envio"*: el programa ni llega a guardar nada.
Con las anotaciones, cada dirección tiene sus columnas `origen_*` y `destino_*`.
(Volver a poner las anotaciones deja el proyecto como se entrega.)
