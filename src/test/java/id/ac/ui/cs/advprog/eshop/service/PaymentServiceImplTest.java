package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private OrderService orderService;

    @Mock
    private Order order;

    @Mock
    private PaymentRepository paymentRepository;

    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> validBankData;
    private Map<String, String> invalidBankData;

    @BeforeEach
    void setUp() {
        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALIDVOUCHER");

        validBankData = new HashMap<>();
        validBankData.put("bankName", "Bank");
        validBankData.put("referenceCode", "12345678");

        invalidBankData = new HashMap<>();
        invalidBankData.put("bankName", "");
        invalidBankData.put("referenceCode", "");
    }

    @Test
    void testAddPaymentWithValidVoucher() {
        Payment payment = paymentService.addPayment(order, "voucher", validVoucherData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());
    }

    @Test
    void testAddPaymentWithInvalidVoucher() {
        Payment payment = paymentService.addPayment(order, "voucher", invalidVoucherData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.FAILED.getValue());
    }

    @Test
    void testAddPaymentWithValidBankTransfer() {
        Payment payment = paymentService.addPayment(order, "bankTransfer", validBankData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testAddPaymentWithInvalidBankTransfer() {
        Payment payment = paymentService.addPayment(order, "bankTransfer", invalidBankData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.FAILED.getValue());
    }

    @Test
    void testAddPaymentWithInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(order, "invalidMethod", validVoucherData);
        });
        verify(paymentRepository, times(0)).save(any(Payment.class));
        verify(orderService, times(0)).updateStatus(anyString(), anyString());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment(UUID.randomUUID().toString(), "voucher", validVoucherData, order);
        paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        verify(paymentRepository, times(0)).save(payment);
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment(UUID.randomUUID().toString(), "voucher", validVoucherData, order);
        paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.FAILED.getValue());
    }

    @Test
    void testGetPayment() {
        Payment payment = new Payment(UUID.randomUUID().toString(), "voucher", validVoucherData, order);
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);

        Payment foundPayment = paymentService.getPayment(payment.getId());
        assertEquals(payment, foundPayment);
    }

    @Test
    void testGetAllPayments() {
        ArrayList<Payment> payments = new ArrayList<>(List.of(
                new Payment(UUID.randomUUID().toString(), "voucher", validVoucherData, order),
                new Payment(UUID.randomUUID().toString(), "bankTransfer", validBankData, order)
        ));
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> foundPayments = paymentService.getAllPayments();
        assertEquals(payments, foundPayments);
    }
}