package sageniuz;

import sageniuz.model.Message;

import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        var emFactory = Persistence.createEntityManagerFactory("helloworld");
        var em = emFactory.createEntityManager();
        em.getTransaction().begin();
        var msg = new Message("Hello World");
        em.persist(msg);
        em.getTransaction().commit();

        em.getTransaction().begin();
        var messages = em
            .createQuery("select m from Message m", Message.class)
            .getResultList();
        var msgFromDb = messages.get(0);
        msgFromDb.setText("Hello Claus");
        em.getTransaction().commit();

        em.close();
        emFactory.close();
    }
}
