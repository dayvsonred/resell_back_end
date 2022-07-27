package com.resell.person.repositories;

import com.resell.person.entities.BilletPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BilletPaymentRepository  extends JpaRepository<BilletPayment, Long> {

    Optional<BilletPayment> findByHash(String hash);
    Optional<BilletPayment> findByBarCode(String barCode);
}
