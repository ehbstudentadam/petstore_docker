package be.ehb.petstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "payments")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment {
    public enum PaymentStatus {
        PAID,       //The payment has been successfully completed.
        PENDING,    //The payment is awaiting processing or confirmation.
        FAILED,     //The payment was attempted but did not go through or was declined.
        REFUNDED,   //The payment has been returned to the payer.
        AUTHORIZED, //The payment has been approved and reserved but not yet captured.
        CHARGEBACK, //The payer disputes the payment, and the funds are returned to them.
        CANCELLED,  //The payment has been canceled or voided before completion.
        OVERDUE,    //The payment is past its due date or has not been made within the agreed timeframe.
        INCOMPLETE, //The payment process has not been finished, and it remains open.
        REVERSED    //The payment was initially successful but later reversed.
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long id;
    private BigDecimal price;
    @Size(max = 30, message = "Message can not be more than 30 characters")
    private String details;
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Payment() {
    }
    public Payment(BigDecimal price, String details, Date paymentDate, PaymentStatus paymentStatus) {
        this.price = price;
        this.details = details;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(getPrice(), payment.getPrice()) && Objects.equals(getDetails(), payment.getDetails()) && Objects.equals(getPaymentDate(), payment.getPaymentDate()) && getPaymentStatus() == payment.getPaymentStatus();
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, getPrice(), getDetails(), getPaymentDate(), getPaymentStatus());
    }
}
