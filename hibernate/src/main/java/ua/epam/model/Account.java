package ua.epam.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  @Id
  @Column(name = "id")
  private UUID id = UUID.randomUUID();

  @Column(name = "data")
  private String data;

  @Enumerated(EnumType.STRING)
  private AccountStatus status;

  @OneToOne(mappedBy = "account")
  private Developer developer;
}
