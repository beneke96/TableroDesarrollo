package es.formacion.cip.fran.demo;

import java.util.List;
import java.util.Properties;

import es.formacion.cip.fran.model.Tarea;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



public class App {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    private static SessionFactory configureSessionFactory() throws HibernateException {
       Configuration configuration = new Configuration();
        configuration.configure();

        Properties properties = configuration.getProperties();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;

    }

    public static void main (String[] args){
    configureSessionFactory();

    Session session = null;
    Transaction tx = null;

    try {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        // Creamos la tarea
        Tarea tarea1 = new Tarea("Corregir Bugs","Se necesita Tetear el codigo y ver donde explota");

        // Guardamos la tarea
        session.save(tarea1);

        // Comitamos los cambios en la base de datos
        session.flush();
        tx.commit();

        // Fetching los datos guardados
        List<Tarea> listaTareas = session.createQuery("from Tarea").list();

        for(Tarea tarea : listaTareas){
            System.out.println("ID: " + tarea.getId()+ "| Nombre: " + tarea.getNombre() +
                    " | Descripcion: " + tarea.getDescripcion());
        }

    }catch (Exception ex){
        ex.printStackTrace();

        // Volver a dejar los datos como estaban en caso de fallo
        tx.rollback();
    }finally {
        if (session != null){
            session.close();
        }
    }

    }
}
