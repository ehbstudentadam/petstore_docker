package be.ehb.petstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Objects;

@Entity(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long id;

    @Size(min = 3, max = 40, message = "Street name must be between 3 and 40 characters")
    private String street;

    @Size(min = 1, max = 10, message = "Street number must be between 1 and 10 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 -]*$", message = "Invalid street number format")
    private String streetNumber;

    @Size(min = 3, max = 20, message = "City name must be between 3 and 20 characters")
    private String city;

    @Size(min = 1, max = 10, message = "Zipcode must be between 1 and 10 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 -]*$", message = "Invalid postal code format")
    private String zip;

    @Size(min = 2, max = 20, message = "Country name must be between 2 and 20 characters")
    private String country;

    @OneToMany(mappedBy = "billingAddress")
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<User> userBillingList;

    @OneToMany(mappedBy = "shippingAddress")
    private List<User> userShippingList;

    public Address() {
    }

    public Address(String street, String streetNumber, String city, String zip, String country, List<User> userBillingList, List<User> userShippingList) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.userBillingList = userBillingList;
        this.userShippingList = userShippingList;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<User> getUserBillingList() {
        return userBillingList;
    }

    public void setUserBillingList(List<User> userBillingList) {
        this.userBillingList = userBillingList;
    }

    public List<User> getUserShippingList() {
        return userShippingList;
    }

    public void setUserShippingList(List<User> userShippingList) {
        this.userShippingList = userShippingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getStreetNumber(), address.getStreetNumber()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getZip(), address.getZip()) && Objects.equals(getCountry(), address.getCountry()) && Objects.equals(getUserBillingList(), address.getUserBillingList()) && Objects.equals(getUserShippingList(), address.getUserShippingList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStreet(), getStreetNumber(), getCity(), getZip(), getCountry(), getUserBillingList(), getUserShippingList());
    }

    @Override
    public String toString() {
        return street + ' ' + streetNumber + ' ' +
                city + ' ' + zip + ' ' + country;
    }
}
