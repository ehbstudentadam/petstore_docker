package be.ehb.petstore.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "paypal")
public class Paypal extends Payment {
    @Size(min = 3, max = 50, message = "Detail must be between 3 and 50 characters")
    private String detail;

    public Paypal() {
        super();
    }
    public Paypal(BigDecimal price, String details, Date paymentDate, PaymentStatus paymentStatus, String detail) {
        super(price, details, paymentDate, paymentStatus);
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Paypal paypal = (Paypal) o;
        return Objects.equals(getDetail(), paypal.getDetail());
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDetail());
    }
}
