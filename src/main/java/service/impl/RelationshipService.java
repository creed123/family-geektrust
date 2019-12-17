package service.impl;

import businessobjects.GetRelationshipBo;
import registry.Relationship;

import java.util.Arrays;
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

    public void printRelations(GetRelationshipBo getRelationshipBo) throws Exception {
        List<String> names = Arrays.stream(Relationship.values())
                .filter(relationship -> relationship.relationship.equals(getRelationshipBo.getRelationShip()))
                .findFirst().orElseThrow(Exception::new)
                .relationshipFinderService.findRelations(getRelationshipBo.getName());
        Optional.ofNullable(names).ifPresent((List<String> result) -> {
            if (result.isEmpty()) {
                System.out.println("NONE");
            } else {
                formatAndPrint(result);
            }
        });

    }
}
