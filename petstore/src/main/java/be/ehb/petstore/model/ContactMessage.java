package be.ehb.petstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity(name = "contact_message")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_message_id")
    private Long id;
    @NotNull(message = "Name can not be empty")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters")
    private String name;
    @NotNull(message = "Email can not be empty")
    @Email
    private String email;
    @NotNull(message = "Subject can not be empty")
    @Size(min = 5, max = 50, message = "Subject must be between 5 and 50 characters")
    private String subject;
    @NotNull(message = "Message can not be empty")
    @Size(min = 50, max = 1000, message = "Message must be between 50 and 1000 characters")
    @Column(length = 1000)
    private String message;

    public ContactMessage() {
    }

    public ContactMessage(String name, String email, String subject, String message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactMessage that = (ContactMessage) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getSubject(), that.getSubject()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getSubject(), getMessage());
    }
}
