package ua.epam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account_status")
public class AccountStatus extends BasicEntity {

    @Column(name = "status")
    private String status;

    public AccountStatus(Long id, String status) {
        super(id);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
