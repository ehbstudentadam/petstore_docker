package be.ehb.petstore.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "bank_transfers")
public class BankTransfer extends Payment {
    @NotNull(message = "Bank number can not be empty")
    @Size(min = 12, max = 20, message = "Bank number must be between 12 and 20 characters")
    private String bankNumber;
    @Size(min = 3, max = 50, message = "Detail must be between 3 and 50 characters")
    private String detail;

    public BankTransfer() {
        super();
    }
    public BankTransfer(BigDecimal price, String details, Date paymentDate, PaymentStatus paymentStatus, String bankNumber, String detail) {
        super(price, details, paymentDate, paymentStatus);
        this.bankNumber = bankNumber;
        this.detail = detail;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
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
        BankTransfer that = (BankTransfer) o;
        return Objects.equals(getBankNumber(), that.getBankNumber()) && Objects.equals(getDetail(), that.getDetail());
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBankNumber(), getDetail());
    }
}
