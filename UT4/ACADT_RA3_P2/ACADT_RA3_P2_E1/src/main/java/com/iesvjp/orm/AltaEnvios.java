package com.iesvjp.orm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.iesvjp.orm.modelo.Direccion;
import com.iesvjp.orm.modelo.Envio;
import com.iesvjp.orm.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Ejercicio 1 · ACADT · RA3 · P2.
 *
 *  1. Guarda tres envíos con persist() en una sola transacción.
 *  2. Recupera cada envío con find() a partir de su id y lo muestra con toString().
 */
public class AltaEnvios {

    // Referencia estática para que el ajuste de nivel de log no se pierda
    private static final Logger LOG_HIBERNATE = Logger.getLogger("org.hibernate");

    public static void main(String[] args) {
        // Oculta los mensajes INFO de arranque de Hibernate (el SQL se sigue mostrando)
        LOG_HIBERNATE.setLevel(Level.WARNING);

        try {
            List<Long> ids = guardarEnvios();
            mostrarEnvios(ids);
        } finally {
            JPAUtil.cerrar();
        }
    }

    // 1. Tres envíos en una sola transacción: o se guardan los tres o no se guarda ninguno
    private static List<Long> guardarEnvios() {
        System.out.println("===== 1. Guardar tres envíos con persist() =====");
        LocalDateTime ahora = LocalDateTime.now();
        LocalDate entrega = LocalDate.now().plusDays(2);

        Envio e1 = new Envio("ENV000000001", "Portátil y accesorios", 3.5, ahora, entrega,
                new Direccion("C/ Mayor, 5", "06800", "Mérida", "Badajoz"),
                new Direccion("Avda. de Portugal, 20", "10001", "Cáceres", "Cáceres"));
        Envio e2 = new Envio("ENV000000002", "Caja de libros", 12.8, ahora, entrega,
                new Direccion("C/ San Pedro, 12", "06002", "Badajoz", "Badajoz"),
                new Direccion("C/ Ancha, 3", "10600", "Plasencia", "Cáceres"));
        // Este envío no tiene fecha de entrega prevista
        Envio e3 = new Envio("ENV000000003", "Paquete de ropa", 1.2, ahora, null,
                new Direccion("C/ Nueva, 1", "06300", "Zafra", "Badajoz"),
                new Direccion("C/ Santa Eulalia, 8", "06800", "Mérida", "Badajoz"));

        List<Long> ids = new ArrayList<>();
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(e1);
            em.persist(e2);
            em.persist(e3);
            tx.commit();
            ids.add(e1.getId());
            ids.add(e2.getId());
            ids.add(e3.getId());
            System.out.println("Guardados los envíos con id " + ids);
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("No se han podido guardar los envíos: " + ex.getMessage());
            ids.clear();
        } finally {
            em.close();
        }
        return ids;
    }

    // 2. Cada envío se recupera con find() en un EntityManager propio
    private static void mostrarEnvios(List<Long> ids) {
        System.out.println("\n===== 2. Recuperar cada envío con find() =====");
        for (Long id : ids) {
            EntityManager em = JPAUtil.getEntityManager();
            try {
                Envio envio = em.find(Envio.class, id);
                System.out.println(envio != null ? envio : "No existe el envío " + id);
            } finally {
                em.close();
            }
        }
    }
}
