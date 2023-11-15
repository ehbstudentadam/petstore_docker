package be.ehb.petstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {

    public enum Provider {
        LOCAL, GOOGLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @NotNull
    private String username;
    private String googleNameId;
    private String password;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    private String lastName;
    @NotEmpty
    @Email
    @Column(unique = true)
    private String emailAddress;
    private LocalDate birthdate;


    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    //@JoinColumn(name = "address_id", nullable = false)
    private Address billingAddress;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address shippingAddress;

    @OneToMany(mappedBy = "user")
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Order> orders;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    public User() {
    }

    public User(String username, String googleNameId, String password, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, String firstName, String lastName, String emailAddress, LocalDate birthdate, Address billingAddress, Address shippingAddress, List<Order> orders, Provider provider) {
        this.username = username;
        this.googleNameId = googleNameId;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthdate = birthdate;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.orders = orders;
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoogleNameId() {
        return googleNameId;
    }

    public void setGoogleNameId(String googleNameId) {
        this.googleNameId = googleNameId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getGoogleNameId(), user.getGoogleNameId()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getAccountNonExpired(), user.getAccountNonExpired()) && Objects.equals(getAccountNonLocked(), user.getAccountNonLocked()) && Objects.equals(getCredentialsNonExpired(), user.getCredentialsNonExpired()) && Objects.equals(getEnabled(), user.getEnabled()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getEmailAddress(), user.getEmailAddress()) && Objects.equals(getBirthdate(), user.getBirthdate()) && Objects.equals(getBillingAddress(), user.getBillingAddress()) && Objects.equals(getShippingAddress(), user.getShippingAddress()) && Objects.equals(getOrders(), user.getOrders()) && getProvider() == user.getProvider();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getGoogleNameId(), getPassword(), getAccountNonExpired(), getAccountNonLocked(), getCredentialsNonExpired(), getEnabled(), getFirstName(), getLastName(), getEmailAddress(), getBirthdate(), getBillingAddress(), getShippingAddress(), getOrders(), getProvider());
    }


}
