package com.minibank.transfers.infrastructure.entity.transfer;

import com.minibank.transfers.infrastructure.entity.transfer.enums.TransferReasonType;
import com.minibank.transfers.infrastructure.entity.transfer.enums.TransferStatusType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


@Entity(name = "tb_transfer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "payee")
    private String payee;

    @Column(name = "payer")
    private String payer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransferStatusType status;

    @Column(name = "reason")
    @Enumerated(EnumType.STRING)
    private TransferReasonType reason;

    @Column(name = "amount")
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
