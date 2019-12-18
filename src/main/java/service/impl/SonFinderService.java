package service.impl;

import model.Gender;
import model.Person;
import service.IRelationshipFinderService;
import service.PersonRegistryService;
import utility.FamilyUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SonFinderService implements IRelationshipFinderService {

    public static SonFinderService sonFinderService;

    @Override
    public List<String> findRelations(String name) {
        try {
            Person person = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(name)).orElseThrow(Exception::new);
            return FamilyUtil.orderByPriority(person.getChildren().stream()
                    .map(rel -> PersonRegistryService.getPersonAccessor().getPerson(rel))
                    .filter(rel -> rel.getGender().equals(Gender.MALE))
                    .collect(Collectors.toList()));
        } catch (Exception exception) {
            PrinterService.getSingletonService().print(Collections.singletonList("PERSON_NOT_FOUND"));
        }
        return null;
    }

    public static IRelationshipFinderService getSingletonService() {
        if (Optional.ofNullable(sonFinderService).isPresent()) {
            return sonFinderService;
        }
        sonFinderService = new SonFinderService();
        return sonFinderService;
    }
}
