package service.impl;

import businessobjects.GetRelationshipBo;
import registry.Relationship;
import utility.FamilyUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RelationshipService {

    public static RelationshipService getRelationshipOperationService;

    public static RelationshipService getSingletonClass() {
        if (Optional.ofNullable(getRelationshipOperationService).isPresent()) {
            return getRelationshipOperationService;
        }
        return new RelationshipService();
    }

    public void printRelations(GetRelationshipBo getRelationshipBo) throws Exception {
        List<String> names = Arrays.asList(Relationship.values()).stream()
                .filter(relationship -> relationship.relationship.equals(getRelationshipBo.getRelationShip()))
                .findFirst().orElseThrow(Exception::new)
                .relationshipFinderService.findRelations(getRelationshipBo.getName());
        FamilyUtil.formatAndPrint(names);
    }
}
