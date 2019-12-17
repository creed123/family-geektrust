package service.impl;

import executor.Family;
import model.Gender;
import model.Person;
import service.IRelationshipFinderService;
import service.PersonRegistryService;
import utility.FamilyUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SisterInLawFinderService implements IRelationshipFinderService {

    public static SisterInLawFinderService sisterInLawFinderService;

    @Override
    public List<String> findRelations(String name) {
            try {
                Person person = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(name)).orElseThrow(Exception::new);
                Person spouse = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(person.getSpouse())).orElseThrow(Exception::new);
                Person spouseMother = Optional.ofNullable(PersonRegistryService.getPersonAccessor().getPerson(spouse.getParent())).orElseThrow(Exception::new);
                List<String> relations = spouseMother.getChildren()
                        .stream()
                        .map(relation -> PersonRegistryService.getPersonAccessor().getPerson(relation))
                        .filter(rel -> rel.getGender().equals(Gender.FEMALE) && !rel.getName().equals(spouse.getName()))
                        .map(Person::getName)
                        .collect(Collectors.toList());
                List<String> siblings = SiblingsFinderService.getSingletonService().findRelations(name);
                relations.addAll(siblings.stream()
                        .map(sib -> PersonRegistryService.getPersonAccessor().getPerson(sib).getSpouse())
                        .filter(sib -> PersonRegistryService.getPersonAccessor().getPerson(sib).getGender().equals(Gender.FEMALE))
                        .collect(Collectors.toList()));
                return FamilyUtil.orderByPriority(relations.stream()
                                .map(rel -> PersonRegistryService.getPersonAccessor().getPerson(rel))
                                .collect(Collectors.toList()));
            } catch (Exception exception) {
                System.out.println("PERSON_NOT_FOUND");
            }
            return Collections.EMPTY_LIST;
    }

    public static IRelationshipFinderService getSingletonService() {
        if (Optional.ofNullable(sisterInLawFinderService).isPresent()) {
            return sisterInLawFinderService;
        }
        sisterInLawFinderService = new SisterInLawFinderService();
        return sisterInLawFinderService;
    }
}
