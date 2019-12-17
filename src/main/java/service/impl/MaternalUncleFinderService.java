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

public class MaternalUncleFinderService implements IRelationshipFinderService {

    public static MaternalUncleFinderService maternalUncleFinderService;

    @Override
    public List<String> findRelations(String name) {
        try {
            Person person = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(name)).orElseThrow(Exception::new);
            Person mother = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(person.getParent())).orElseThrow(Exception::new);
            Person grandMother = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(mother.getParent())).orElseThrow(Exception::new);
            return FamilyUtil.orderByPriority(grandMother.getChildren()
                    .stream()
                    .map(relation -> PersonRegistryService.getPersonAccessor().getPerson(relation))
                    .filter(rel -> rel.getGender().equals(Gender.MALE))
                    .collect(Collectors.toList()));
        } catch (Exception exception) {
            System.out.println("PERSON_NOT_FOUND");
        }
        return null;
    }

    public static IRelationshipFinderService getSingletonService() {
        if (Optional.ofNullable(maternalUncleFinderService).isPresent()) {
            return maternalUncleFinderService;
        }
        maternalUncleFinderService = new MaternalUncleFinderService();
        return maternalUncleFinderService;
    }
}
