package service.impl;

import businessobjects.AddChildBo;
import businessobjects.AddSpouseBo;
import businessobjects.printRelationshipBo;

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

    public void resolveAndExecute(List<String> lineContents) throws Exception {
        if (lineContents.get(0).equals("ADD_CHILD")) {
            AddChildBo addChildBo = new AddChildBo(lineContents.get(1), lineContents.get(2), lineContents.get(3));
            AddChildOperationService.getSingletonClass().addChild(addChildBo);
        } else if (lineContents.get(0).equals("GET_RELATIONSHIP")) {
            printRelationshipBo printRelationshipBo = new printRelationshipBo(lineContents.get(1), lineContents.get(2));
            RelationshipService.getSingletonClass().printRelations(printRelationshipBo);
        } else if (lineContents.get(0).equals("ADD_SPOUSE")) {
            AddSpouseBo addSpouseBo = new AddSpouseBo(lineContents.get(1), lineContents.get(2), lineContents.get(3));
            AddSpouseService.getSingletonClass().addSpouse(addSpouseBo);
        }
    }
}
