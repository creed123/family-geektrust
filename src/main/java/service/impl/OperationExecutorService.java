package service.impl;

import businessobjects.AddChildBo;
import businessobjects.GetRelationshipBo;

import java.util.List;
import java.util.Optional;

public class OperationExecutorService {

    public static OperationExecutorService operationExecutorService;

    public static OperationExecutorService getSingletonService() {
        if (Optional.ofNullable(operationExecutorService).isPresent()) {
            return operationExecutorService;
        }
        return new OperationExecutorService();
    }

    public void resolveOperationAndExecute(List<String> lineContents) throws Exception {
        if (lineContents.get(0).equals("ADD_CHILD")) {
            AddChildBo addChildBo = new AddChildBo(lineContents.get(1), lineContents.get(0), lineContents.get(2));
            AddChildOperationService.getSingletonClass().addChild(addChildBo);
        } else if (lineContents.get(0).equals("GET_RELATIONSHIP")) {
            GetRelationshipBo getRelationshipBo = new GetRelationshipBo(lineContents.get(1), lineContents.get(2));
            RelationshipService.getSingletonClass().printRelations(getRelationshipBo);
        }
    }
}
