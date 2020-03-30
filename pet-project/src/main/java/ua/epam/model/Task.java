package ua.epam.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

  @Id
  @Column(name = "id")
  private UUID id = UUID.randomUUID();

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "developer_id")
  private Developer appointedDeveloper;

}
