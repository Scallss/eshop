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
    }

    public void setMethod(String method) {
    }

    public void setPaymentData(Map<String, String> paymentData) {
    }

    private void validatePayment() {
    }
}