package com.jpmc.midascore.component;

import org.springframework.stereotype.Service;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.repository.UserRepository;

@Service
public class TransactionService {
    
    private UserRepository userRepo;

    public TransactionService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    //to get User balance from userRepo
    public Balance getBalancebyID(long userId) {

        Balance bal = new Balance();
        try{
            UserRecord record = userRepo.findById(userId);

            float balance = record.getBalance();

            bal.setAmount(balance);

            return bal;
        }
        catch(Exception e){
            float num = 0.0f;
            bal.setAmount(num);
            return bal;
        }
    }
}
