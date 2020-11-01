package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.module.order.domain.DeliveryInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryInformationRepository extends JpaRepository<DeliveryInformation, Long> {
}
