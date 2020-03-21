package ua.epam.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class Skill extends BasicEntity {

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
  private Set<Developer> developers;

  public Skill() {
  }

  public Skill(Long id, String name) {
    super(id);
    this.name = name;
  }

  public Long getId() {
    return super.getId();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Skill skill = (Skill) o;
    return Objects.equals(getName(), skill.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public String toString() {
    return "Skill{" + "id=" + getId() +
        "name='" + name + '\'' +
        '}';
  }
}
