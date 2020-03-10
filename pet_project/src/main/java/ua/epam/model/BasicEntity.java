package ua.epam.model;

import javax.persistence.*;

@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public BasicEntity(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
