package com.demo.mapper;


import com.demo.pojo.entity.Voucher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface VoucherMapper {

    @Insert("insert into tb_voucher(id, stock) values(#{id}, #{stock})")
    Integer save(Voucher voucher);

    @Select("select id, stock from tb_voucher where id=#{voucherId}")
    Voucher findById(Long voucherId);

    @Update("update tb_voucher set stock=stock-1 where id=#{voucherId} and stock>0")
    Integer deductStock(Long voucherId);

}
