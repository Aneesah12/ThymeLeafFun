package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {
    private static final String CITY1 = "Phnom Penh";
    private static final String CITY2 = "Manchester";
    private static final String EMPLOYER_INITIAL = "S";
    private static final String SECOND_NAME_INITIAL = "Qu";
    private static final String FIRST_NAME_INITIAL = "A";
    private static final String JOB_TITLE_INITIAL = "D";
    private static final String FIRST_NAME = "Aneesah";

    private static final List<Person> SOME_PEOPLE = new ArrayList<Person>() {{
        add(new Person("Rob", "Carey", CITY1, "UN", "Diplomat", LocalDate.of(1990, 9, 18)));
        add(new Person("Aneesah", "Quraishi", CITY2, "Sky IE", "Developer", LocalDate.of(1999, 9, 18)));
        add(new Person("Annie", "Qu", CITY2, "Sky IED", "HR", LocalDate.of(1999, 9, 18)));
    }};

    private static final List<Person> SOME_NEW_PEOPLE = new ArrayList<Person>() {{
        add(new Person("Ellie", "Ross", CITY1, "UN", "Diplomat", LocalDate.of(1990, 9, 18)));
        add(new Person("Ben", "Coombs", CITY2, "Sky DE", "Top Developer", LocalDate.of(1999, 9, 18)));
        add(new Person("Hameed", "Adigun", CITY2, "Hameed Town", "QA", LocalDate.of(1999, 9, 18)));
        add(new Person("Tom", "Bassindale", CITY2, "DevOps", "Dev Principal", LocalDate.of(1999, 9, 18)));
    }};

    @Mock
    private PersonDao personDao;

    @InjectMocks
    private LocationService locationService;

    @Before
    public void setup() {
        when(personDao.getAll()).thenReturn(SOME_PEOPLE);
    }

    @Test
    public void filterByCityWhenCityNameIsProvided() {
        List<Person> selectedPeopleByCity = locationService.filterByCity(CITY1);
        assertThat(selectedPeopleByCity, hasSize(1));
    }

    @Test
    public void findAllRecordsWhenNoCityNameIsProvided() {
        List<Person> selectedPeopleByCity = locationService.filterByCity(null);
        assertThat(selectedPeopleByCity, hasSize(3));
    }

    @Test
    public void findNoRecordsWhenCityNameIsNotMatched() {
        List<Person> selectedPeopleByCity = locationService.filterByCity("ksks");
        assertThat(selectedPeopleByCity, hasSize(0));
    }

    @Test
    public void findCompanyThatIsSearchedForByInitialLetterOfCompanyName() {
        List<Person> selectedPeopleByEmployer = locationService.filterByEmployer(EMPLOYER_INITIAL);
        assertThat(selectedPeopleByEmployer, hasSize(2));
        assertThat(selectedPeopleByEmployer.get(0).getEmployer(), is("Sky IE"));
        assertThat(selectedPeopleByEmployer.get(1).getEmployer(), is("Sky IED"));
    }

    @Test
    public void findCompanyThatIsSearchedForByInitialLetterOfCompanyNameWhenLowerCase() {
        List<Person> selectedPeopleByEmployer = locationService.filterByEmployer(EMPLOYER_INITIAL.toLowerCase());
        assertThat(selectedPeopleByEmployer, hasSize(2));
        assertThat(selectedPeopleByEmployer.get(0).getEmployer(), is("Sky IE"));
        assertThat(selectedPeopleByEmployer.get(1).getEmployer(), is("Sky IED"));
    }

    @Test
    public void findCompanyThatIsExactMatchOnlyOfCompanyNameThatIsSearchedFor() {
        List<Person> selectedPeopleByEmployer = locationService.filterByEmployer("Sky IED");
        assertThat(selectedPeopleByEmployer, hasSize(1));
    }

    @Test
    public void findNoCompanyNameRecordWhenInitialLettersDoNotMatchAnyRecord() {
        List<Person> selectedPeopleByEmployer = locationService.filterByEmployer("Ss");
        assertThat(selectedPeopleByEmployer, hasSize(0));
    }

    @Test
    public void findAllPeopleWhenNoCompanyLetterIsProvided() {
        List<Person> selectedPeopleByEmployer = locationService.filterByEmployer(null);
        assertThat(selectedPeopleByEmployer, hasSize(3));
        assertThat(selectedPeopleByEmployer, is(SOME_PEOPLE));
    }

    @Test
    public void findAllPeopleWhenJobTitleLetterIsProvided() {
        List<Person> selectedPeopleByJobTitle = locationService.filterByJobTitle(JOB_TITLE_INITIAL);
        assertThat(selectedPeopleByJobTitle, hasSize(2));
        assertThat(selectedPeopleByJobTitle.get(0).getJobTitle(), is("Diplomat"));
        assertThat(selectedPeopleByJobTitle.get(1).getJobTitle(), is("Developer"));
    }

    @Test
    public void findAllPeopleWhenNoJobTitleLetterIsProvided() {
        List<Person> selectedPeopleByJobTitle = locationService.filterByJobTitle(null);
        assertThat(selectedPeopleByJobTitle, hasSize(3));
        assertThat(selectedPeopleByJobTitle, is(SOME_PEOPLE));
    }

    @Test
    public void findPeopleRecordsWhenSearchingByFirstName() {
        List<Person> selectedPeopleByFirstName = locationService.filterByFirstName(FIRST_NAME_INITIAL);
        assertThat(selectedPeopleByFirstName, hasSize(2));
        assertThat(selectedPeopleByFirstName.get(0).getFirstName(), is("Aneesah"));
        assertThat(selectedPeopleByFirstName.get(1).getFirstName(), is("Annie"));
    }
     @Test
     public void findPeopleRecordsWhenSearchingByFirstFullName() {
        List<Person> selectedPeopleByFirstName = locationService.filterByFirstName(FIRST_NAME);
        assertThat(selectedPeopleByFirstName, hasSize(1));
        assertThat(selectedPeopleByFirstName.get(0).getFirstName(), is("Aneesah"));
    }

    @Test
    public void findAllPeopleRecordsWhenNoFirstNameIsProvidedInSearch() {
        List<Person> selectedPeopleByFirstName = locationService.filterByFirstName(null);
        assertThat(selectedPeopleByFirstName, hasSize(3));
        assertThat(selectedPeopleByFirstName, is(SOME_PEOPLE));
    }

    @Test
    public void findPeopleRecordsWhenSearchingBySecondName() {
        List<Person> selectedPeopleBySecondName = locationService.filterBySecondName(SECOND_NAME_INITIAL);
        assertThat(selectedPeopleBySecondName, hasSize(2));
        assertThat(selectedPeopleBySecondName.get(0).getSecondName(), is("Quraishi"));
        assertThat(selectedPeopleBySecondName.get(1).getSecondName(), is("Qu"));
    }

    @Test
    public void findPeopleRecordsWhenSearchingByFirstAndSecondName() {
        List<Person> selectedPeopleBySecondName = locationService.filterByFirstAndSecondName(FIRST_NAME_INITIAL, SECOND_NAME_INITIAL);
        assertThat(selectedPeopleBySecondName, hasSize(2));
        assertThat(selectedPeopleBySecondName.get(0).getFirstAndSecondName(), is("Aneesah, Quraishi"));
        assertThat(selectedPeopleBySecondName.get(1).getFirstAndSecondName(), is("Annie, Qu"));
    }

    @Test
    public void savesUserInput() {
        locationService.saveAll(SOME_NEW_PEOPLE);
    }

    //test to filter by first and second name
    //combined searches
    //having user input
}


