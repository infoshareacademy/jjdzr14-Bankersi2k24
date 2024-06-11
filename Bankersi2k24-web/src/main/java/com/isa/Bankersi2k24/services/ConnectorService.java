package com.isa.Bankersi2k24.services;

import com.isa.Bankersi2k24.dataAccess.Connector;
import com.isa.Bankersi2k24.dataAccess.FileNames;
import com.isa.Bankersi2k24.dataAccess.Serializable;
import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.Transaction;
import com.isa.Bankersi2k24.models.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConnectorService {
    public <T extends Serializable<T>> Integer generateNewId(Class<T> tClass){
        String fileName;
        Comparator<T> comparator;
        if(tClass == User.class) {
            fileName = FileNames.USER.toString();
            comparator = (Comparator<T>) Comparator.comparing(User::getId);
        } else if (tClass == BankAccount.class) {
            fileName = FileNames.BANKACCOUNT.toString();
            comparator = (Comparator<T>) Comparator.comparing(BankAccount::getId);
        } else if (tClass == Transaction.class) {
            fileName = FileNames.TRANSACITON.toString();
            comparator = (Comparator<T>) Comparator.comparing(Transaction::getId);
        }else{
            return -1;
        }

        Connector connector = new Connector(fileName);
        List<T> objects = (List<T>) T.fetchAllObjects();

        return (objects.isEmpty()) ? 0 : Collections.max(objects, comparator).getId()+1;
    }
}
