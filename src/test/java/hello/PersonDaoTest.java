package hello;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonDaoTest {

    private PersonDao personDao;

    @Before
    public void setUp() throws Exception {
        //todo create an instance of a personDao
        PersonDao personDao = new PersonDao();
    }

    @Test
    public void shouldBeAbleToCreateNewUser() {
        personDao.addPerson(new Person());

        //todo assert on this...

    }
}