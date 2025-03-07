package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(UUID.randomUUID().toString(), method, paymentData, order);

        if ("voucher".equals(method)) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") && voucherCode.chars().filter(Character::isDigit).count() == 8) {
                this.setStatus(payment, PaymentStatus.SUCCESS.getValue());
            } else {
                this.setStatus(payment, PaymentStatus.REJECTED.getValue());
            }
        } else if ("bankTransfer".equals(method)) {
            String bankName = paymentData.get("bankName");
            String referenceCode = paymentData.get("referenceCode");
            if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
                this.setStatus(payment, PaymentStatus.REJECTED.getValue());
            } else {
                this.setStatus(payment, PaymentStatus.SUCCESS.getValue());
            }
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }

        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if (PaymentStatus.SUCCESS.getValue().equals(status)) {
            orderService.updateStatus(payment.getOrder().getId(), OrderStatus.SUCCESS.getValue());
        } else if (PaymentStatus.REJECTED.getValue().equals(status)) {
            orderService.updateStatus(payment.getOrder().getId(), OrderStatus.FAILED.getValue());
        }
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}