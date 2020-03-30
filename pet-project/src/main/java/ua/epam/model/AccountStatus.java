package ua.epam.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_status")
@Data()
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatus {

  @Id
  @Column(name = "id")
  private UUID id = UUID.randomUUID();

  @Column(name = "status")
  private String status;

}
