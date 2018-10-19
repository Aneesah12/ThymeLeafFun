package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class LocationService {

    private PersonDao personDao;

    @Autowired
    public LocationService(PersonDao personDao) {
        this.personDao = personDao;
    }


    public List<Person> filterByCity(String city) {
        Predicate<Person> personPredicate = p -> city == null || p.getCity().toLowerCase().startsWith(city.toLowerCase());
        return getCollect(personPredicate);
    }

    public List<Person> filterByEmployer(String employer) {
        Predicate<Person> personPredicate = p -> employer == null || p.getEmployer().toLowerCase().startsWith(employer.toLowerCase());
        return getCollect(personPredicate);
    }

    public List<Person> filterByJobTitle(String jobTitle) {
        Predicate<Person> personPredicate = p -> jobTitle == null || p.getJobTitle().toLowerCase().startsWith(jobTitle.toLowerCase());
        return getCollect(personPredicate);
    }

    public List<Person> filterByFirstName(String firstName) {
        Predicate<Person> personPredicate = p -> firstName == null || p.getFirstName().toLowerCase().startsWith(firstName.toLowerCase());
        return getCollect(personPredicate);
    }

    public List<Person> filterBySecondName(String secondName) {
        Predicate<Person> personPredicate = p -> secondName == null || p.getSecondName().toLowerCase().startsWith(secondName.toLowerCase());
        return getCollect(personPredicate);
    }

    private List<Person> getCollect(Predicate<Person> personPredicate) {
        return personDao.getAll().stream()
                .filter(personPredicate)
                .collect(Collectors.toList());
    }

    public List<Person> filterByFirstAndSecondName(String firstName, String secondName) {
        return personDao.getAll().stream()
                .filter(p -> p.getFirstName().toLowerCase().startsWith(firstName.toLowerCase()))
                .filter(p -> p.getSecondName().toLowerCase().startsWith(secondName.toLowerCase()))
                        .collect(Collectors.toList());
    }

    public List<Person> showAllValues() {
        return personDao.getAll();
    }


    public void saveAll(List<Person> people) {
        for(Person person : people) {

            //todo drop this down to the dao to action this...
            System.out.println(person);
        }
    }
}

