package com.tju.elmboot.mapper;

import com.tju.elmboot.po.TransactionEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WalletTransactionMapper {
    public void saveTransaction(TransactionEntity transactionEntity);
}
