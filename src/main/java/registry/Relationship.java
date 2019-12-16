package registry;

import service.IRelationshipFinderService;
import service.impl.BrotherInLawFinderService;
import service.impl.DaughterFinderService;
import service.impl.MaternalAuntFinderService;
import service.impl.MaternalUncleFinderService;
import service.impl.PaternalAuntFinderService;
import service.impl.PaternalUncleFinderService;
import service.impl.SiblingsFinderService;
import service.impl.SisterInLawFinderService;
import service.impl.SonFinderService;

public enum Relationship {
    PATERNAL_UNCLE("Paternal-Uncle", PaternalUncleFinderService.getSingletonService()),
    MATERNAL_UNCLE("Maternal-Uncle", MaternalUncleFinderService.getSingletonService()),
    PATERNAL_AUNT("Paternal-Aunt", PaternalAuntFinderService.getSingletonService()),
    MATERNAL_AUNT("Maternal-Aunt", MaternalAuntFinderService.getSingletonService()),
    SISTER_IN_LAW("Sister-In-Law", SisterInLawFinderService.getSingletonService()),
    BROTHER_IN_LAW("Brother-In-Law", BrotherInLawFinderService.getSingletonService()),
    SON("Son", SonFinderService.getSingletonService()),
    DAUGHTER("Daughter", DaughterFinderService.getSingletonService()),
    SIBLINGS("Siblings", SiblingsFinderService.getSingletonService());

    public String relationship;

    public IRelationshipFinderService relationshipFinderService;

    Relationship(String relationship, IRelationshipFinderService relationshipFinderService) {
        this.relationship = relationship;
        this.relationshipFinderService = relationshipFinderService;
    }
}
