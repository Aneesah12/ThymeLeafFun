package hello;

import java.time.LocalDate;

public class Person {

    private String firstName;
    private String secondName;
    private String city;
    private String employer;
    private String jobTitle;
    private LocalDate dateOfBirth;

    public Person() {

    }

    public Person(String firstName, String secondName, String city, String employer, String jobTitle, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.city = city;
        this.employer = employer;
        this.jobTitle = jobTitle;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName == null || firstName.isEmpty() ? "No Name " : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSecondName() {
        return secondName == null || secondName.isEmpty() ? " No Name" : secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstAndSecondName() {
        return firstName + ", " + secondName;
    }
}
