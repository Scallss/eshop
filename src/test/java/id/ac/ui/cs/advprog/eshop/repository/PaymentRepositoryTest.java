package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    @InjectMocks
    private Order order;

    private PaymentRepository paymentRepository;
    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucher", "ESHOP1234ABC5678");
        payment1 = new Payment("paymentId1", "voucher", paymentData1, order);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "referenceCode");
        payment2 = new Payment("paymentId2", "bankTransfer", paymentData2, order);
    }

    @Test
    void testSavePayment() {
        Payment savedPayment = paymentRepository.save(payment1);
        assertEquals(payment1, savedPayment);
    }

    @Test
    void testFindByIdIfIdFound() {
        paymentRepository.save(payment1);
        Payment foundPayment = paymentRepository.findById(payment1.getId());
        assertEquals(payment1, foundPayment);
    }

    @Test
    void testFindByIdIfIdNotFound() {
        paymentRepository.save(payment1);
        Payment foundPayment = paymentRepository.findById("nonExistentId");
        assertNull(foundPayment);
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
        assertEquals(2, paymentRepository.findAll().size());
    }

    @Test
    void testFindAllWhenEmpty() {
        assertTrue(paymentRepository.findAll().isEmpty());
    }
}