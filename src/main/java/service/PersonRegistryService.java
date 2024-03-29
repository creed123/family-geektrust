package service;

import model.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PersonRegistryService {

    private static PersonRegistryService personRegistryService;

    private Map<String, Person> personNameToDetails;

    public static PersonRegistryService getPersonAccessor() {
        if (Optional.ofNullable(personRegistryService).isPresent()) {
            return personRegistryService;
        }
        personRegistryService = new PersonRegistryService();
        personRegistryService.personNameToDetails = new HashMap<>();
        return personRegistryService;
    }

    public Person getPerson(String name) {
        if (personNameToDetails.containsKey(name)) {
            return personNameToDetails.get(name);
        }
        return null;
    }

    public void registerPerson(String name, Person person) throws Exception {
        if (personNameToDetails.containsKey(name)) {
            System.out.println("throwing exception");
            throw new Exception("Duplicate name entered");
        }
        personNameToDetails.put(name, person);
    }
}
