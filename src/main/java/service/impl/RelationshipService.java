package service.impl;

import businessobjects.printRelationshipBo;
import registry.Relationship;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static utility.FamilyUtil.*;

public class RelationshipService {

    public static RelationshipService getRelationshipOperationService;

    public static RelationshipService getSingletonClass() {
        if (Optional.ofNullable(getRelationshipOperationService).isPresent()) {
            return getRelationshipOperationService;
        }
        return new RelationshipService();
    }

    public void printRelations(printRelationshipBo printRelationshipBo) throws Exception {
        List<String> names = Arrays.stream(Relationship.values())
                .filter(relationship -> relationship.relationship.equals(printRelationshipBo.getRelationShip()))
                .findFirst().orElseThrow(Exception::new)
                .relationshipFinderService.findRelations(printRelationshipBo.getName());
        Optional.ofNullable(names).ifPresent((List<String> result) -> {
            if (result.isEmpty()) {
                PrinterService.getSingletonService().print(Collections.singletonList("NONE"));
            } else {
                formatAndPrint(result);
            }
        });

    }
}
