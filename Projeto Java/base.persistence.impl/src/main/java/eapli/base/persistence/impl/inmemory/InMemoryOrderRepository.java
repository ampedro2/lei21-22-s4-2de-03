package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrderRepository  extends InMemoryDomainRepository<ProductOrder, Long>
        implements OrderRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public ProductOrder findByOrderId(Long orderId) {
        return null;
    }

    @Override
    public ProductOrder update(ProductOrder order) {
        return null;
    }
}
