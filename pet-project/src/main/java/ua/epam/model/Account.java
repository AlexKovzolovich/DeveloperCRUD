package ua.epam.model;

import java.util.UUID;
import javax.persistence.*;
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

  @ManyToOne
  @JoinColumn(name = "status")
  private AccountStatus status;

  @OneToOne(mappedBy = "account")
  private Developer developer;


}
