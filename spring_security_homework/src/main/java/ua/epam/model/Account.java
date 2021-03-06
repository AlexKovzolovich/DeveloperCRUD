package ua.epam.model;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account extends BasicEntity {

  @Column(name = "data")
  private String data;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "status")
  private AccountStatus status;

  @OneToOne(mappedBy = "account")
  private Developer developer;

  public Account() {
  }

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

  public void setData(String data) {
    this.data = data;
  }

  public AccountStatus getStatus() {
    return status;
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
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(getId(), account.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
