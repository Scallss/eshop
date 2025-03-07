package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @InjectMocks
    private Order order;

    @Test
    void testValidVoucherPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucher", "ESHOP1234ABC5678");

        Payment payment = new Payment("paymentId", "voucher", paymentData, order);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testInvalidVoucherPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucher", "INVALIDVOUCHER");

        Payment payment = new Payment("paymentId", "voucher", paymentData, order);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testValidBankTransferPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "referenceCode");

        Payment payment = new Payment("paymentId", "bankTransfer", paymentData, order);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testInvalidBankTransferPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");

        Payment payment = new Payment("paymentId", "bankTransfer", paymentData, order);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucher", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("paymentId", "invalidMethod", paymentData, order);
        });
    }

    @Test
    void testInvalidPaymentDataSize() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucher", "ESHOP1234ABC5678");
        paymentData.put("extraKey", "extraValue");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("paymentId", "voucher", paymentData, order);
        });
    }
}