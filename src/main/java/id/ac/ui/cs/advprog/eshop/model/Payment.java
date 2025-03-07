package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;


    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        setMethod(method);
        setPaymentData(paymentData);
        this.order = order;
        validatePayment();
    }

    public void setMethod(String method) {
        if (!"voucher".equals(method) && !"bankTransfer".equals(method)) {
            throw new IllegalArgumentException("Method must be either 'voucher' or 'bankTransfer'");
        }
        this.method = method;
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.size() != 1) {
            throw new IllegalArgumentException("PaymentData must contain exactly one key-value pair");
        }
        this.paymentData = paymentData;
    }

    private void validatePayment() {
        if ("voucher".equals(method)) {
            String voucherCode = paymentData.get("voucher");
            if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") && voucherCode.chars().filter(Character::isDigit).count() == 8) {
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }
        } else if ("bankTransfer".equals(method)) {
            String bankName = paymentData.keySet().iterator().next();
            String referenceCode = paymentData.get(bankName);
            if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
                this.status = "REJECTED";
            } else {
                this.status = "SUCCESS";
            }
        }
    }
}