package service.impl;

import businessobjects.AddSpouseBo;
import model.Gender;
import model.Person;
import service.CounterService;
import service.PersonRegistryService;

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

    public void addSpouse(AddSpouseBo addSpouseBo) throws Exception {
        Person spouse = PersonRegistryService.getPersonAccessor().getPerson(addSpouseBo.getSpouseName());
        if (Optional.ofNullable(spouse).isPresent()) {
            Person person = new Person(addSpouseBo.getName(), Gender.getGender(addSpouseBo.getGender()),
                    Collections.EMPTY_LIST, spouse.getName(), CounterService.incrementAndGet(), null);
            PersonRegistryService.getPersonAccessor().registerPerson(person.getName(), person);
        }
    }

}