package sageniuz;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import sageniuz.model.Message;

import javax.persistence.Persistence;
import javax.validation.Validation;

public class App {
    public static void main(String[] args) {
        var emFactory = Persistence.createEntityManagerFactory("helloworld");
        var em = emFactory.createEntityManager();
        em.getTransaction().begin();
        var msg = new Message(null);

        var validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

        var violations = validator.validate(msg);
        System.out.println("====> " + violations); // shows constraint violation

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
