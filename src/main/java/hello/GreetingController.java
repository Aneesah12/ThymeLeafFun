package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    private final LocationService locationService;
    private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    public GreetingController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "secondName", required = false) String secondName,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "employer", required = false) String employer,
            @RequestParam(name = "jobTitle", required = false) String jobTitle,
            Model model) {

//move into a switch statement...?
        if (city != null) {
            model.addAttribute("place", city);
            model.addAttribute("people", locationService.filterByCity(city));
        } else if (employer != null) {
            model.addAttribute("employer", employer);
            model.addAttribute("people", locationService.filterByEmployer(employer));
        } else if(jobTitle != null) {
            model.addAttribute("jobTitle", jobTitle);
            model.addAttribute("people", locationService.filterByJobTitle(jobTitle));
        } else if(firstName != null && secondName != null) {
            LOG.info("****I got here first!!!***");
            model.addAttribute("firstName", firstName);
            model.addAttribute("secondName", secondName);
            model.addAttribute("people", locationService.filterByFirstAndSecondName(firstName, secondName));
        } else {
            model.addAttribute("people", locationService.showAllValues());
        }
        return "greeting";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        PersonDao personForm = new PersonDao();

        for (int i = 1; i <= 3; i++) {
            personForm.addPerson(new Person());
        }

        model.addAttribute("form", personForm);
        return "create";

    }


    @PostMapping("/greeting")
    public String savePeople(@ModelAttribute PersonCreationDto form, Model model) {
        locationService.saveAll(form.getPeople());

        model.addAttribute("people", locationService.showAllValues());
        System.out.println(locationService.showAllValues());
        return "greeting";
    }
}


//@PostMapping
//LOG.info("****I got here!!!***");
//        model.addAttribute("secondName", secondName);
//        model.addAttribute("people", locationService.filterBySecondName(secondName));

//        model.addAttribute("people", locationService.getAll());
//            model.addAttribute("secondName", secondName);
//            model.addAttribute("people", locationService.filterBySecondName(secondName));


//add in some logging or System.out.println to work out why firstName is not working on the webpage
//@RequestParam(name = "secondName", required = false) String secondName,
// LOG.info("{}", this.resultCounter.getResult());
//LOG.warn("Ignoring duplicate movie: [{}] - {} - ({})", movie.getId(), movie.getHeadline(), movie.getYear());
