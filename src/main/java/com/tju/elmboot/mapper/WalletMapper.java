package com.tju.elmboot.mapper;

import com.tju.elmboot.po.VirtualWallet;
import com.tju.elmboot.po.VirtualWalletTransaction;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WalletMapper {

    @Select("select * from wallet where userId=#{userId}")
    public VirtualWallet getWalletMessage(String userId);

    @Update("update wallet set balance=#{balance} where userId=#{userId}")
    public int updataBalance(String userId,double balance);

    @Insert("insert into walletTransaction(transactionTime,amount,transactionType,inId,outId) values(#{transactionTime},#{amount},#{transactionType},#{inId},#{outId})")
    //@Options(useGeneratedKeys = true, keyProperty = "transactionId", keyColumn = "transactionId")
    public int saveTransaction(int transactionId,String transactionTime,double amount,int transactionType,String inId,String outId);

    public List<VirtualWalletTransaction> listTransaction(String userId);

    @Insert("insert into wallet(userId,createTime,balance) values(#{userId},#{createTime},#{balance})")
    public int saveWallet(String userId,String createTime,double balance);

    @Update("update wallet set balance=balance-#{amount} where userId=#{outId}")
    public void withdraw(@Param("outId") String outId, @Param("amount") Double amount);

    @Update("update wallet set balance=balance+#{amount} where userId=#{inId}")
    public void fund(@Param("inId") String inId, @Param("amount") Double amount);
}
