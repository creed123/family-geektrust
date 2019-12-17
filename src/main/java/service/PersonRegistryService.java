package service;

import model.Person;

import java.util.Map;
import java.util.Optional;

public class PersonRegistryService {

    private static PersonRegistryService personRegistryService;

    private Map<String, Person> personNameToDetails;

    public static PersonRegistryService getPersonAccessor() {
        if (Optional.ofNullable(personRegistryService).isPresent()) {
            return personRegistryService;
        }
        return new PersonRegistryService();
    }

    public Person getPerson(String name) {
        if (personNameToDetails.containsKey(name)) {
            return personNameToDetails.get(name);
        }
        return null;
    }

    public void registerPerson(String name, Person person) throws Exception {
        if (personNameToDetails.containsKey(name)) {
            //TODO add custom exception here.
            throw new Exception();
        }
        personNameToDetails.put(name, person);
    }
}
