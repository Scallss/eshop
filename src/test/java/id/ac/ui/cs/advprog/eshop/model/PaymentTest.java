package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @InjectMocks
    private Order order;

    private Payment payment;

    @BeforeEach
    void setUp() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        payment = new Payment("paymentId", "VOUCHER", paymentData, order);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("paymentId", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
        assertEquals(order, payment.getOrder());

        payment.setId("newPaymentId");
        payment.setMethod("bankTransfer");
        payment.setStatus("SUCCESS");
        payment.getPaymentData().put("bankName", "Bank");
    }

    @Test
    void testConstructor() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment newPayment = new Payment("newPaymentId", "bankTransfer", paymentData, order);
        assertEquals("newPaymentId", newPayment.getId());
        assertEquals("bankTransfer", newPayment.getMethod());
        assertEquals("ESHOP1234ABC5678", newPayment.getPaymentData().get("voucherCode"));
        assertEquals(order, newPayment.getOrder());
    }
}