package ua.epam.model;

import java.util.Objects;
import java.util.Set;

public class Developer extends BasicEntity{
    private String name;
    private Account account;
    private Set<Skill> skills;

    public Developer(Long id, String name, Account account, Set<Skill> skills) {
        super(id);
        this.name = name;
        this.account = account;
        this.skills = skills;
    }

    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" + "id=" + getId() +
                "name='" + name + '\'' +
                ", account=" + account +
                ", skills=" + skills +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(getId(), developer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
