package service.impl;

import businessobjects.AddChildBo;
import model.Gender;
import model.Person;
import service.CounterService;
import service.PersonRegistryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class AddChildOperationService {

    public static AddChildOperationService addChildOperationService;

    public static AddChildOperationService getSingletonClass() {
        if (Optional.ofNullable(addChildOperationService).isPresent()) {
            return addChildOperationService;
        }
        addChildOperationService = new AddChildOperationService();
        return addChildOperationService;
    }

    public void addChild(AddChildBo addChildBo) throws Exception {
        Person mother = PersonRegistryService.getPersonAccessor().getPerson(addChildBo.getMotherName());
        if (Optional.ofNullable(mother).isPresent()
                && Optional.ofNullable(mother.getSpouse()).isPresent()
                && Optional.ofNullable(mother).get().getGender().equals(Gender.FEMALE)) {
            mother.getChildren().add(addChildBo.getName());
            Person child = new Person(addChildBo.getName(), Gender.getGender(addChildBo.getGender()), new ArrayList<>(), null, CounterService.incrementAndGet(), addChildBo.getMotherName());
            PersonRegistryService.getPersonAccessor().registerPerson(addChildBo.getName(), child);
            PrinterService.getSingletonService().print(Collections.singletonList("CHILD_ADDITION_SUCCEEDED"));
        } else {
            PrinterService.getSingletonService().print(Collections.singletonList("CHILD_ADDITION_FAILED"));
        }
    }
}
