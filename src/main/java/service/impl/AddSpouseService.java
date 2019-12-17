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
        return new AddSpouseService();
    }

    public void addSpouse(AddSpouseBo addSpouseBo) {
        try {
            Person spouse = PersonRegistryService.getPersonAccessor().getPerson(addSpouseBo.getSpouseName());
            if (Optional.ofNullable(spouse).isPresent()) {
                Person person = new Person(addSpouseBo.getName(), Gender.getGender(addSpouseBo.getGender()),
                        new ArrayList<>(), spouse.getName(), CounterService.incrementAndGet(), null);
                PersonRegistryService.getPersonAccessor().registerPerson(person.getName(), person);
            }
        } catch (Exception exception) {
            System.out.println("SPOUSE_ADDITION_FAILED");
        }
    }

}