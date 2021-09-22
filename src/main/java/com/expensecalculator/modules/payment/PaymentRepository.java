package com.expensecalculator.modules.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment save(Payment payment);

    @Transactional
    @Modifying
    void deleteById(Long paymentId);
}
