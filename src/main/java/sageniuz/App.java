package sageniuz;

import jakarta.persistence.Persistence;
import sageniuz.model.Message;


public class App {
    public static void main(String[] args) {
        var emFactory = Persistence.createEntityManagerFactory("helloworld");
        var em = emFactory.createEntityManager();
        em.getTransaction().begin();
        var msg = new Message(null);

        em.persist(msg);
        em.getTransaction().commit(); // ignores bean validation

        em.getTransaction().begin();
        var messages = em
            .createQuery("select m from Message m", Message.class)
            .getResultList();
        var msgFromDb = messages.get(0);
        msgFromDb.setText("Hello again");
        em.getTransaction().commit();

        em.close();
        emFactory.close();
    }
}
