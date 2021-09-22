package com.expensecalculator.modules.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface PaymentQueryRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAll();

    Optional<Payment> findByIdPayment(Long paymentId);
}
