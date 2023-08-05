package com.demo.mapper;

import com.demo.pojo.entity.VoucherOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface VoucherOrderMapper {

    @Insert("insert into tb_voucher_order(id, userId, voucherId) values(#{id}, #{userId}, #{voucherId})")
    Integer save(VoucherOrder voucherOrder);

    @Select("select count(*) from tb_voucher_order where userId=#{userId} and voucherId=#{voucherId}")
    Integer find(@Param("userId") Long userId, @Param("voucherId") Long voucherId);
}
