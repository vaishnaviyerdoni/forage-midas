package com.jpmc.midascore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Setter @Getter 
@NoArgsConstructor
@AllArgsConstructor

public class TransactionRecord {

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    private UserRecord sender;

    @ManyToOne
    private UserRecord recipient;

    private double amount;

    private float incentive;

    @Override
    public String toString() {
        return String.format( "senderId=%d, recipientId = %d, balance = %f, incentive=%f", sender, recipient, amount, incentive);
    }
}
