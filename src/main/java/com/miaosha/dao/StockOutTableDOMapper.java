package com.miaosha.dao;

import com.miaosha.dataobject.StockOutTableDO;
import org.springframework.stereotype.Repository;

@Repository
public interface StockOutTableDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_out_table
     *
     * @mbg.generated Wed Apr 17 09:01:58 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_out_table
     *
     * @mbg.generated Wed Apr 17 09:01:58 CST 2019
     */
    int insert(StockOutTableDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_out_table
     *
     * @mbg.generated Wed Apr 17 09:01:58 CST 2019
     */
    int insertSelective(StockOutTableDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_out_table
     *
     * @mbg.generated Wed Apr 17 09:01:58 CST 2019
     */
    StockOutTableDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_out_table
     *
     * @mbg.generated Wed Apr 17 09:01:58 CST 2019
     */
    int updateByPrimaryKeySelective(StockOutTableDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_out_table
     *
     * @mbg.generated Wed Apr 17 09:01:58 CST 2019
     */
    int updateByPrimaryKey(StockOutTableDO record);
}