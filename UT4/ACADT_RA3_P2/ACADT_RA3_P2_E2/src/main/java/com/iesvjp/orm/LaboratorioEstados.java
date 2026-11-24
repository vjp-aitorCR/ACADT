package com.iesvjp.orm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.iesvjp.orm.modelo.Direccion;
import com.iesvjp.orm.modelo.Envio;
import com.iesvjp.orm.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Ejercicio 2 · ACADT · RA3 · P2 · Laboratorio de estados de una entidad.
 *
 * Estados: transient (nuevo) -> managed (gestionado) -> detached (separado) / removed (eliminado).
 *
 * Al empezar se guardan tres envíos de ejemplo (ids 1, 2 y 3) y cada paso trabaja con uno:
 *   Paso 1 -> envío nuevo (id 4)      Paso 2 -> envío 1 (solo lectura)
 *   Paso 3 -> envío 2                 Paso 4 -> envío 3 (queda detached)
 *   Paso 5 -> envío 1                 Paso 6 -> el envío 3 del paso 4
 *   Paso 7 -> envío 2 (se elimina)    Paso 8 -> envío 1 (detached) e id 999
 *   Paso 9 -> envío nuevo + código repetido del envío 1
 *
 * Observa en la consola el SQL que lanza Hibernate en cada paso y rellena la tabla del documento.
 */
public class LaboratorioEstados {

    // Referencia estática para que el ajuste de nivel de log no se pierda
    private static final Logger LOG_HIBERNATE = Logger.getLogger("org.hibernate");

    private static final String CODIGO_EXISTENTE = "ENV000000001";

    public static void main(String[] args) {
        // Oculta los mensajes INFO de arranque de Hibernate (el SQL se sigue mostrando)
        LOG_HIBERNATE.setLevel(Level.WARNING);

        try {
            Long[] ids = guardarEjemplos();
            if (ids == null) {
                return;
            }
            Long id1 = ids[0];
            Long id2 = ids[1];
            Long id3 = ids[2];

            paso1Guardar();
            paso2ContextoDePersistencia(id1);
            paso3Modificar(id2);
            Envio separado = paso4SepararAlCerrar(id3);
            paso5SepararConDetach(id1);
            if (separado != null) {
                paso6VolverAGestionar(separado);
            }
            paso7Eliminar(id2);
            paso8ErroresProvocados(id1);
            paso9Rollback();
        } finally {
            JPAUtil.cerrar();
        }
    }

    // ---------- Datos de partida ----------

    private static Long[] guardarEjemplos() {
        titulo("Datos de partida: tres envíos de ejemplo");
        Envio a = nuevoEnvio("ENV000000001", "Portátil y accesorios", 3.5, "Mérida", "Cáceres");
        Envio b = nuevoEnvio("ENV000000002", "Caja de libros", 12.8, "Badajoz", "Plasencia");
        Envio c = nuevoEnvio("ENV000000003", "Paquete de ropa", 1.2, "Zafra", "Mérida");

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(a);
            em.persist(b);
            em.persist(c);
            tx.commit();
            System.out.println("Guardados los envíos con id " + a.getId() + ", " + b.getId() + " y " + c.getId());
            return new Long[] { a.getId(), b.getId(), c.getId() };
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("No se han podido guardar los envíos de ejemplo: " + ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    // ---------- Paso 1 ----------
    // persist(): de transient a managed. Con IDENTITY el INSERT se lanza en el propio persist()
    private static void paso1Guardar() {
        titulo("1. Guardar con persist()");
        Envio envio = nuevoEnvio("ENV000000004", "Cajas de cerámica", 8.0, "Cáceres", "Badajoz");
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            System.out.println("Antes de persist():   id = " + envio.getId()
                    + ", em.contains = " + em.contains(envio));           // null, false
            tx.begin();
            em.persist(envio);
            System.out.println("Después de persist(): id = " + envio.getId()
                    + ", em.contains = " + em.contains(envio));           // 4, true (antes del commit)
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Paso 1 cancelado: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    // ---------- Paso 2 ----------
    // Dentro de un mismo EntityManager, el mismo id devuelve siempre el mismo objeto
    private static void paso2ContextoDePersistencia(Long id) {
        titulo("2. Contexto de persistencia");
        EntityManager em = JPAUtil.getEntityManager();
        try {
            System.out.println("--- Cuenta las SELECT que aparecen a partir de aquí ---");
            Envio a = em.find(Envio.class, id);   // SELECT
            Envio b = em.find(Envio.class, id);   // sin SELECT: ya está en el contexto
            System.out.println("--- Fin del recuento ---");
            System.out.println("¿a == b? " + (a == b));   // true
        } finally {
            em.close();
        }
    }

    // ---------- Paso 3 ----------
    // Con transacción, el cambio se guarda (dirty checking). Sin transacción, no
    private static void paso3Modificar(Long id) {
        titulo("3. Modificar");
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Envio envio = em.find(Envio.class, id);        // managed
            envio.setPeso(15.5);
            tx.commit();                                   // UPDATE automático

            // El objeto sigue gestionado, pero ya no hay transacción: nadie volcará este cambio
            envio.setDescripcion("Descripción cambiada SIN transacción");
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Paso 3 cancelado: " + ex.getMessage());
        } finally {
            em.close();    // cerrar no hace flush: la descripción se pierde
        }

        Envio recuperado = buscar(id);
        System.out.println("En la base de datos -> peso: " + recuperado.getPeso()
                + ", descripción: " + recuperado.getDescripcion());
    }

    // ---------- Paso 4 ----------
    // Al cerrar el EntityManager el objeto pasa a detached: sus cambios ya no se guardan
    private static Envio paso4SepararAlCerrar(Long id) {
        titulo("4. Separar al cerrar el EntityManager");
        EntityManager em = JPAUtil.getEntityManager();
        Envio envio;
        try {
            envio = em.find(Envio.class, id);
        } finally {
            em.close();    // envio pasa a detached
        }

        envio.setDestino(new Direccion("C/ Cambiada, 99", "28001", "Madrid", "Madrid"));
        System.out.println("Destino en memoria:            " + envio.getDestino());
        System.out.println("Destino en la base de datos:   " + buscar(id).getDestino());
        return envio;
    }

    // ---------- Paso 5 ----------
    // detach() separa un objeto sin cerrar el EntityManager
    private static void paso5SepararConDetach(Long id) {
        titulo("5. Separar con detach()");
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Envio envio = em.find(Envio.class, id);        // managed
            em.detach(envio);                              // detached
            System.out.println("em.contains después de detach(): " + em.contains(envio));   // false
            envio.setPeso(99.0);                           // no se guardará
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Paso 5 cancelado: " + ex.getMessage());
        } finally {
            em.close();
        }
        System.out.println("Peso en la base de datos: " + buscar(id).getPeso()
                + " (si fuera 99.0 el cambio se habría guardado)");
    }

    // ---------- Paso 6 ----------
    // merge() copia el estado del objeto detached en uno managed y devuelve ESTE último
    private static void paso6VolverAGestionar(Envio separado) {
        titulo("6. Volver a gestionar con merge()");
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Envio gestionado = em.merge(separado);         // SELECT; copia el estado
            System.out.println("¿gestionado == separado? " + (gestionado == separado));   // false
            System.out.println("em.contains(gestionado): " + em.contains(gestionado)      // true
                    + ", em.contains(separado): " + em.contains(separado));               // false

            separado.setPeso(777.0);                       // cambia el ORIGINAL, no el devuelto
            tx.commit();                                   // UPDATE solo con lo copiado en merge()
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Paso 6 cancelado: " + ex.getMessage());
        } finally {
            em.close();
        }
        Envio enBD = buscar(separado.getId());
        System.out.println("En la base de datos -> destino: " + enBD.getDestino()
                + ", peso: " + enBD.getPeso() + " (777.0 no se ha guardado)");
    }

    // ---------- Paso 7 ----------
    // remove() solo admite objetos managed: primero find() y después remove()
    private static void paso7Eliminar(Long id) {
        titulo("7. Eliminar con remove()");
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Envio envio = em.find(Envio.class, id);
            em.remove(envio);                              // removed
            System.out.println("em.contains antes del commit: " + em.contains(envio));   // false
            tx.commit();                                   // DELETE
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Paso 7 cancelado: " + ex.getMessage());
        } finally {
            em.close();
        }
        System.out.println("¿Sigue existiendo el envío " + id + "? " + (buscar(id) != null));   // false
    }

    // ---------- Paso 8 ----------
    private static void paso8ErroresProvocados(Long idExistente) {
        titulo("8. Errores provocados");
        Envio separado = buscar(idExistente);   // el EntityManager ya está cerrado: detached

        // 8.1 remove() de un envío detached
        EntityManager em1 = JPAUtil.getEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        try {
            tx1.begin();
            em1.remove(separado);
            tx1.commit();
            System.out.println("8.1 No ha fallado (inesperado)");
        } catch (RuntimeException ex) {
            if (tx1.isActive()) {
                tx1.rollback();
            }
            System.out.println("8.1 remove() de un detached -> " + ex.getClass().getSimpleName()
                    + ": " + ex.getMessage());
        } finally {
            em1.close();
        }

        // 8.2 persist() de un envío detached
        EntityManager em2 = JPAUtil.getEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        try {
            tx2.begin();
            em2.persist(separado);
            tx2.commit();
            System.out.println("8.2 No ha fallado (inesperado)");
        } catch (RuntimeException ex) {
            if (tx2.isActive()) {
                tx2.rollback();
            }
            System.out.println("8.2 persist() de un detached -> " + ex.getClass().getSimpleName()
                    + ": " + ex.getMessage());
        } finally {
            em2.close();
        }

        // 8.3 find() de un id que no existe: no es un error, devuelve null
        EntityManager em3 = JPAUtil.getEntityManager();
        try {
            Envio inexistente = em3.find(Envio.class, 999L);
            System.out.println("8.3 find(999) -> " + inexistente);   // null
        } catch (RuntimeException ex) {
            System.out.println("8.3 find(999) -> " + ex.getClass().getSimpleName()
                    + ": " + ex.getMessage());
        } finally {
            em3.close();
        }

        // 8.4 getReference() devuelve un proxy sin consultar; el error llega al usarlo
        EntityManager em4 = JPAUtil.getEntityManager();
        try {
            Envio referencia = em4.getReference(Envio.class, 999L);   // sin SELECT
            System.out.println("8.4 getReference(999) devuelto sin error (todavía no se ha consultado)");
            System.out.println(referencia.getDescripcion());          // SELECT -> excepción
        } catch (RuntimeException ex) {
            System.out.println("8.4 getDescripcion() sobre el proxy -> " + ex.getClass().getSimpleName()
                    + ": " + ex.getMessage());
        } finally {
            em4.close();
        }
    }

    // ---------- Paso 9 ----------
    // rollback() deshace la transacción en la base de datos, pero no el estado de los objetos en memoria
    private static void paso9Rollback() {
        titulo("9. Rollback");
        Envio correcto = nuevoEnvio("ENV000000005", "Envío correcto", 2.0, "Mérida", "Badajoz");
        Envio repetido = nuevoEnvio(CODIGO_EXISTENTE, "Envío con código repetido", 4.0, "Zafra", "Cáceres");

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(correcto);      // INSERT: el primer envío recibe su id
            em.persist(repetido);      // INSERT con código de seguimiento duplicado: error
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Operación cancelada -> " + ex.getClass().getSimpleName()
                    + ": " + ex.getMessage());
        } finally {
            em.close();
        }
        System.out.println("Id en memoria del primer envío: " + correcto.getId());
        System.out.println("¿Existe en la base de datos? "
                + (correcto.getId() != null && buscar(correcto.getId()) != null));   // false
    }

    // ---------- Métodos auxiliares ----------

    private static Envio nuevoEnvio(String codigo, String descripcion, double peso,
            String poblacionOrigen, String poblacionDestino) {
        return new Envio(codigo, descripcion, peso, LocalDateTime.now(), LocalDate.now().plusDays(2),
                new Direccion("C/ Origen, 1", "06800", poblacionOrigen, "Badajoz"),
                new Direccion("C/ Destino, 2", "10001", poblacionDestino, "Cáceres"));
    }

    /** Recupera un envío con un EntityManager propio. El objeto devuelto queda detached. */
    private static Envio buscar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Envio.class, id);
        } finally {
            em.close();
        }
    }

    private static void titulo(String texto) {
        System.out.println("\n===== " + texto + " =====");
    }
}
