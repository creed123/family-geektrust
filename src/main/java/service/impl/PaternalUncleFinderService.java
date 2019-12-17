package service.impl;

import model.Gender;
import model.Person;
import service.IRelationshipFinderService;
import service.PersonRegistryService;
import utility.FamilyUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaternalUncleFinderService implements IRelationshipFinderService {

    public static PaternalUncleFinderService paternalUncleFinderService;


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
                        .filter(rel -> rel.getGender().equals(Gender.MALE) && !rel.getName().equals(father.getName()))
                        .collect(Collectors.toList()));
            } catch (Exception exception) {
                System.out.println("PERSON_NOT_FOUND");
            }
            return null;
    }

    public static IRelationshipFinderService getSingletonService() {
        if (Optional.ofNullable(paternalUncleFinderService).isPresent()) {
            return paternalUncleFinderService;
        }
        paternalUncleFinderService = new PaternalUncleFinderService();
        return paternalUncleFinderService;
    }
}
