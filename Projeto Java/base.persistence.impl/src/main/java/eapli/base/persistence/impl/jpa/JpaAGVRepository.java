package eapli.base.persistence.impl.jpa;

import eapli.base.Warehouse.domain.AGVDock;
import eapli.base.Warehouse.domain.AGVDockIdentifier;
import eapli.base.Warehouse.repositories.AGVDockRepository;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVIdentifier;
import eapli.base.agvmanagement.repositories.AGVRepository;

public class JpaAGVRepository extends BasepaRepositoryBase<AGV, AGVIdentifier, AGVIdentifier>
        implements AGVRepository {

    JpaAGVRepository(){
        super("agvIdentifier");
    }
}

