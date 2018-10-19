package hello;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {

    private List<Person> people;

    public PersonDao() {
        people = new ArrayList<>();
        people.add(new Person("Annie", "Quraishi","Manchester", "Sky", "Diplomat",
                LocalDate.of(1999, Month.JUNE, 12)));
        people.add(new Person("Rob", "Carey", "Phnom Penh", "UN", "Diplomat",
                LocalDate.of(2000, Month.APRIL, 28)));
        people.add(new Person("Peter", "Rabbit", "Phnom Penh", "Sky IED", "Developer",
                LocalDate.of(1923, Month.MARCH, 15)));
        people.add(new Person("Kate", "Broadbent", "Phnom Penh", "Horticon", "Landscape Architect",
                LocalDate.of(1997, Month.MARCH, 30)));
        people.add(new Person(null, null, "London", "BBC", "TV Presenter",
                LocalDate.of(1923, Month.MAY, 3)));
        people.add(new Person("", "", "London", "BBC", "TV Presenter",
                LocalDate.of(1923, Month.MAY, 3)));
        people.add(new Person("Sarah", "Qu", "London", "BBC", "TV Presenter",
                LocalDate.of(1923, Month.MAY, 3)));
        people.add(new Person("Roger", "Rabbit", "London", "BBC", "TV Presenter",
                LocalDate.of(1923, Month.MAY, 3)));
    }

    public List<Person> getAll() {
        return this.people;
    }

    public void addPerson(Person person) {
        System.out.println(person);
        System.out.println(this.people);
        this.people.add(person);
    }


    //todo write a method to create/update/delete users...
}
