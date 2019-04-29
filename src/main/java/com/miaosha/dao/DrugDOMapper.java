package com.miaosha.dao;

import com.miaosha.dataobject.DrugDO;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug
     *
     * @mbg.generated Thu Apr 18 09:08:13 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug
     *
     * @mbg.generated Thu Apr 18 09:08:13 CST 2019
     */
    int insert(DrugDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug
     *
     * @mbg.generated Thu Apr 18 09:08:13 CST 2019
     */
    int insertSelective(DrugDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug
     *
     * @mbg.generated Thu Apr 18 09:08:13 CST 2019
     */
    DrugDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug
     *
     * @mbg.generated Thu Apr 18 09:08:13 CST 2019
     */
    int updateByPrimaryKeySelective(DrugDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug
     *
     * @mbg.generated Thu Apr 18 09:08:13 CST 2019
     */
    int updateByPrimaryKey(DrugDO record);
}