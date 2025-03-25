package com.example.appapi.payment.model;

public enum PaymentStatus {
    PENDING,     // 결제 대기
    COMPLETED,   // 결제 완료
    FAILED,      // 결제 실패
    REFUNDED     // 환불 처리
}
