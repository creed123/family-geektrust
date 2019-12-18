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

public class BrotherInLawFinderService implements IRelationshipFinderService {

    public static BrotherInLawFinderService brotherInLawFinderService;

    public static IRelationshipFinderService getSingletonService() {
        if (Optional.ofNullable(brotherInLawFinderService).isPresent()) {
            return brotherInLawFinderService;
        }
        brotherInLawFinderService = new BrotherInLawFinderService();
        return brotherInLawFinderService;
    }

    @Override
    public List<String> findRelations(String name) {
        try {
            List<String> relations = new ArrayList<>();
            Person person = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(name)).orElseThrow(Exception::new);
            Person spouse = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(person.getSpouse())).orElse(null);
            if (Optional.ofNullable(spouse).isPresent()) {
                Person spouseMother = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(spouse.getParent())).orElse(null);
                if (Optional.ofNullable(spouseMother).isPresent()) {
                    relations = spouseMother.getChildren()
                            .stream()
                            .map(relation -> PersonRegistryService.getPersonAccessor().getPerson(relation))
                            .filter(rel -> rel.getGender().equals(Gender.MALE) && !rel.getName().equals(spouse.getName()))
                            .map(Person::getName)
                            .collect(Collectors.toList());
                }
            }
            List<String> siblings = SiblingsFinderService.getSingletonService().findRelations(name);
            relations.addAll(siblings.stream()
                    .map(sib -> PersonRegistryService.getPersonAccessor().getPerson(sib).getSpouse())
                    .filter(sib -> PersonRegistryService.getPersonAccessor().getPerson(sib).getGender().equals(Gender.MALE))
                    .collect(Collectors.toList()));
            return FamilyUtil.orderByPriority(relations.stream()
                    .map(rel -> PersonRegistryService.getPersonAccessor().getPerson(rel))
                    .collect(Collectors.toList()));
        } catch (Exception exception) {
            PrinterService.getSingletonService().print(Collections.singletonList("PERSON_NOT_FOUND"));
        }

        return null;
    }
}
