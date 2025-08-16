package com.jpmc.midascore.component;

import org.springframework.stereotype.Component;
import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepo;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.web.client.RestTemplate;

@Component
public class TransactiontoDB {
    
    private UserRepository user;
    private TransactionRecordRepo transactionRecordRepo;
    private RestTemplate rest;

    public TransactiontoDB(UserRepository user, TransactionRecordRepo transactionRecordRepo, RestTemplate rest){
        this.user = user;
        this.transactionRecordRepo = transactionRecordRepo;
        this.rest = rest;
    }

    @SuppressWarnings("null")
    public String addTransaction(Transaction trans) {

        UserRecord sender = user.findById(trans.getSenderId());
        UserRecord recipient = user.findById(trans.getRecipientId());


        if(sender != null && recipient != null) {
            if(sender.getBalance() >= trans.getAmount()) {

                String url = "http://localhost:8080/incentive";

                Incentive bal = rest.postForObject(url, trans, Incentive.class);

                float incentiveAmt = bal.getAmount();
                
                sender.setBalance(sender.getBalance() - trans.getAmount());
                recipient.setBalance(recipient.getBalance() + trans.getAmount() + incentiveAmt);

                user.save(sender);
                user.save(recipient);

                TransactionRecord trecord = new TransactionRecord();
                trecord.setSender(sender);
                trecord.setRecipient(recipient);
                trecord.setAmount(trans.getAmount());
                trecord.setIncentive(incentiveAmt);
                transactionRecordRepo.save(trecord);

                return "Transaction Successful";
            }
            else{
                return "Sender balance cannot be smaller than amount";
            }
        }
        else{
            return "Invalid Transaction";
        }
    }
}
