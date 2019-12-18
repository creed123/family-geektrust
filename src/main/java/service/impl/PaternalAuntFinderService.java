package service.impl;

import model.Gender;
import model.Person;
import service.IRelationshipFinderService;
import service.PersonRegistryService;
import utility.FamilyUtil;

import java.util.ArrayList;
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
            Person mother = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(person.getParent())).orElse(null);
            List<String> relations = new ArrayList<>();
            if (Optional.ofNullable(mother).isPresent()) {
                Person father = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(mother.getSpouse())).orElse(null);
                if (Optional.ofNullable(father).isPresent()) {
                    relations= FamilyUtil.orderByPriority(SiblingsFinderService.getSingletonService().findRelations(father.getName())
                            .stream()
                            .map(relation -> PersonRegistryService.getPersonAccessor().getPerson(relation))
                            .filter(rel -> rel.getGender().equals(Gender.FEMALE))
                            .collect(Collectors.toList()));
                }
            }
            return relations;
        } catch (Exception exception) {
            PrinterService.getSingletonService().print(Collections.singletonList("PERSON_NOT_FOUND"));
        }
        return null;
    }

    public static IRelationshipFinderService getSingletonService() {
        if (Optional.ofNullable(paternalAuntFinderService).isPresent()) {
            return paternalAuntFinderService;
        }
        paternalAuntFinderService = new PaternalAuntFinderService();
        return paternalAuntFinderService;
    }

}
