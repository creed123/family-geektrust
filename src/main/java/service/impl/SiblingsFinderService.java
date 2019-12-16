package service.impl;

import model.Person;
import service.IRelationshipFinderService;
import service.PersonRegistryService;
import utility.FamilyUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SiblingsFinderService implements IRelationshipFinderService {

    public static SiblingsFinderService siblingsFinderService;

    @Override
    public List<String> findRelations(String name) {
            try {
                Person person = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(name)).orElseThrow(Exception::new);
                Person mother = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(person.getParent())).orElseThrow(Exception::new);
                return FamilyUtil.orderByPriority(mother.getChildren()
                        .stream()
                        .map(relation -> PersonRegistryService.getPersonAccessor().getPerson(relation))
                        .filter(rel -> !rel.getName().equals(person.getName()))
                        .collect(Collectors.toList()));
            } catch (Exception exception) {
                System.out.println("PERSON_NOT_FOUND");
            }
            return Collections.EMPTY_LIST;
    }

    public static IRelationshipFinderService getSingletonService() {
        if (Optional.ofNullable(siblingsFinderService).isPresent()) {
            return siblingsFinderService;
        }
        return new SiblingsFinderService();
    }
}
