package com.tju.elmboot.mapper;

import com.tju.elmboot.po.CreditBO;
import com.tju.elmboot.po.CreditEntity;
import com.tju.elmboot.po.CreditVO;
import com.tju.elmboot.po.ValidCredit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreditMapper {

    public List<ValidCredit> getValidCredit(String userId);

    public List<CreditVO> getCreditTotalDetails(String userId);

    public List<CreditVO> getCreditOfAdd(String userId);

    public List<CreditVO> getCreditOfSpend(String userId);

    public List<CreditVO> getCreditOfOutTime(String userId);

    public int saveCreditDetail(CreditEntity creditEntity);
}
