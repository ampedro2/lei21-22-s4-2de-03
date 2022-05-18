package eapli.base.ordermanagement.application;

import eapli.base.clientusermanagement.domain.ClientUser;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.domain.ProductOrderBuilder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.orderstatusmanagement.domain.Status;
import eapli.base.orderstatusmanagement.repositories.StatusRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.time.util.Calendars;

@UseCaseController
public class UpdateOrderStatusController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final StatusRepository statusRepository = PersistenceContext.repositories().status();

    public ProductOrder UpdateOrderToDispatched(String orderId){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE);
        long statusid = 7;
        Status status = statusRepository.findByStatusId(statusid);
        final ProductOrder order = orderRepository.findByOrderId(Long.parseLong(orderId));
        order.modifyStatus(status);
        return orderRepository.save(order);
    }
}
