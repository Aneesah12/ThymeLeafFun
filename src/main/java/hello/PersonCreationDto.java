package hello;

import java.util.ArrayList;
import java.util.List;

public class PersonCreationDto {

    private List<Person> people;

    public PersonCreationDto() {
        this.people = new ArrayList<>();
    }

    public PersonCreationDto(List<Person> people) {
        this.people = people;
    }

    public void addPerson(Person person) {
        System.out.println(person);
        System.out.println(this.people);
        this.people.add(person);
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

}
