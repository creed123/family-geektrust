package service.impl;

import businessobjects.AddSpouseBo;
import model.Gender;
import model.Person;
import service.CounterService;
import service.PersonRegistryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class AddSpouseService {

    public static AddSpouseService addSpouseService;

    public static AddSpouseService getSingletonClass() {
        if (Optional.ofNullable(addSpouseService).isPresent()) {
            return addSpouseService;
        }
        addSpouseService = new AddSpouseService();
        return addSpouseService;
    }

    public void addSpouse(AddSpouseBo addSpouseBo) {
        try {
            Person spouse = PersonRegistryService.getPersonAccessor().getPerson(addSpouseBo.getSpouseName());
            if (Optional.ofNullable(spouse).isPresent()) {
                Person person = new Person(addSpouseBo.getName(), Gender.getGender(addSpouseBo.getGender()),
                        new ArrayList<>(), spouse.getName(), CounterService.incrementAndGet(), null);
                PersonRegistryService.getPersonAccessor().registerPerson(person.getName(), person);
                spouse.setSpouse(person.getName());
                PrinterService.getSingletonService().print(Collections.singletonList("SPOUSE_ADDITION_SUCCEEDED"));
            }
        } catch (Exception exception) {
            PrinterService.getSingletonService().print(Collections.singletonList("SPOUSE_ADDITION_FAILED"));
        }
    }

}