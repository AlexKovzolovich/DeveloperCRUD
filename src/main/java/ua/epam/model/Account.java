package ua.epam.model;

import java.util.Objects;

public class Account extends BasicEntity{
    private String data;
    private AccountStatus status;

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

    public enum AccountStatus {
        ACTIVE, BANNED, DELETED
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
