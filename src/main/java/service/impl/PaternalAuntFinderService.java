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

public class PaternalAuntFinderService implements IRelationshipFinderService {

    public static PaternalAuntFinderService paternalAuntFinderService;

    @Override
    public List<String> findRelations(String name) {
        try {
            Person person = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(name)).orElseThrow(Exception::new);
            Person mother = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(person.getParent())).orElseThrow(Exception::new);
            Person father = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(mother.getSpouse())).orElseThrow(Exception::new);
            Person grandMother = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(father.getParent())).orElseThrow(Exception::new);
            return FamilyUtil.orderByPriority(grandMother.getChildren()
                    .stream()
                    .map(relation -> PersonRegistryService.getPersonAccessor().getPerson(relation))
                    .filter(rel -> rel.getGender().equals(Gender.FEMALE))
                    .collect(Collectors.toList()));
        } catch (Exception exception) {
            System.out.println("PERSON_NOT_FOUND");
        }
        return Collections.EMPTY_LIST;
    }

    public static IRelationshipFinderService getSingletonService() {
        if (Optional.ofNullable(paternalAuntFinderService).isPresent()) {
            return paternalAuntFinderService;
        }
        paternalAuntFinderService = new PaternalAuntFinderService();
        return paternalAuntFinderService;
    }

}
