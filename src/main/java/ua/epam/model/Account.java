package ua.epam.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account extends BasicEntity{
    @Column(name = "data")
    private String data;

    @ManyToOne
    @JoinColumn(name = "status")
    private AccountStatus status;

    @OneToOne(mappedBy = "account")
    private Developer developer;

    public Account(Long id, String data, AccountStatus status) {
        super(id);
        this.data = data;
        this.status = status;
    }

    public Long getId() {
        return super.getId();
    }

    public String getData() {
        return data;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + getId() +
                "data='" + data + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
