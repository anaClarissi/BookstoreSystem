package model.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Author {

    private Integer id;
    private String name;
    private LocalDate birthDate;

    public Author () {}

    public Author(Integer id, String name, LocalDate birthDate) {

        this.id = id;

        this.name = name;

        this.birthDate = birthDate;

    }

    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public LocalDate getBirthDate() {

        return birthDate;

    }

    public void setBirthDate(LocalDate birthDate) {

        this.birthDate = birthDate;

    }

    @Override
    public boolean equals(Object object) {

        if (object == null || getClass() != object.getClass()) return false;

        Author author = (Author) object;

        return Objects.equals(id, author.id) && Objects.equals(name, author.name);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);

    }

    @Override
    public String toString() {

        return getId() + " | " + getName() + " | " + getBirthDate();

    }

}
