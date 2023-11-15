package be.ehb.petstore.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserForm {

    @NotNull
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters.")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters.")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
