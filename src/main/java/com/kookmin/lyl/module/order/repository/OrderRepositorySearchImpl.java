package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.infra.support.LylQuerydslRepositorySupport;
import com.kookmin.lyl.module.member.domain.QMember;
import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderStatus;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.order.domain.QOrder;
import com.kookmin.lyl.module.order.dto.OrderDetails;
import com.kookmin.lyl.module.order.dto.OrderSearchCondition;
import com.kookmin.lyl.module.order.dto.QOrderDetails;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.swing.text.StyledEditorKit;

import static com.kookmin.lyl.module.member.domain.QMember.member;
import static com.kookmin.lyl.module.order.domain.QOrder.*;
import static com.kookmin.lyl.module.product.domain.QProduct.product;

@Repository
public class OrderRepositorySearchImpl extends LylQuerydslRepositorySupport implements OrderRepositorySearch {
    public OrderRepositorySearchImpl() {
        super(Order.class);
    }

    @Override
    public Page<OrderDetails> searchOrderDetails(Pageable pageable, OrderSearchCondition condition) {
        return applyPagination(pageable, contentQuery -> contentQuery
                .select(new QOrderDetails(
                        order.id, order.totalPrice, order.deliveryAddress, order.request, order.status.stringValue(),
                        order.orderType.stringValue(), member.memberId))
                .from(order)
                .leftJoin(order.member, member)
                .where(orderIdEq(condition.getOrderId()),
                        orderStatusEq(condition.getOrderStatus()),
                        orderTypeEq(condition.getOrderType()),
                        memberIdEq(condition.getMemberId()))
                );
    }

    @Override
    public Order findCartByMemberMemberIdAndOrderType(String memberId, String orderType) {
        return getQueryFactory()
                .selectFrom(order)
                .leftJoin(order.member, member)
                .where(member.memberId.eq(memberId),
                        order.orderType.eq(OrderType.valueOf(orderType)))
                .fetchOne();
    }

    private BooleanExpression orderIdEq(Long orderId) {
        return orderId == null ? null : order.id.eq(orderId);
    }

    private BooleanExpression orderStatusEq(String status) {
        return status == null ? null : order.status.eq(OrderStatus.valueOf(status));
    }

    private BooleanExpression orderTypeEq(String orderType) {
        return orderType == null ? null : order.orderType.eq(OrderType.valueOf(orderType));
    }

    private BooleanExpression memberIdEq(String memberId) {
        return memberId == null ? null : member.memberId.eq(memberId);
    }
}
