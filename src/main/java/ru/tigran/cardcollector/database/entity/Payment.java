package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int totalAmount;

    @Column(length = 16, nullable = false)
    private String invoicePayload;

    @Column(length = 127, nullable = false)
    private String telegramPaymentChargeId;

    @Column(length = 127, nullable = false)
    private String providerPaymentChargeId;
}
