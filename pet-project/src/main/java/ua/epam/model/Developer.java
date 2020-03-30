package ua.epam.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "developers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

  @Id
  @Column(name = "id")
  private UUID id = UUID.randomUUID();

  @Column(name = "name")
  private String name;

  @OneToOne
  @JoinColumn(name = "account")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Account account;

  @ManyToMany
  @JoinTable(name = "developer_skills",
      joinColumns = {@JoinColumn(name = "developer_id")},
      inverseJoinColumns = {@JoinColumn(name = "skill_id")})
  private Set<Skill> skills = new HashSet<>();

  @OneToMany()
  private Set<Task> tasks = new HashSet<>();

}
