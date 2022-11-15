package sageniuz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String text;

    // Generated methods via IDE

    protected Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !Objects.equals(getClass(), o.getClass())) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equals(id, message.id)
            && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + id +
            ", text='" + text + '\'' +
            '}';
    }
}
