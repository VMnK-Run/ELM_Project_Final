package com.tju.elmboot.mapper;

import com.tju.elmboot.po.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CartMapper {

    public List<Cart> listCart(Cart cart);

    @Insert("insert into cart values(null,#{foodId},#{businessId},#{userId},1)")
    public int saveCart(Cart cart);

    @Update("update cart set quantity=#{quantity} where foodId=#{foodId} and businessId=# {businessId} and userId=#{userId}")
    public int updateCart(Cart cart);

    public int removeCart(Cart cart);
}
