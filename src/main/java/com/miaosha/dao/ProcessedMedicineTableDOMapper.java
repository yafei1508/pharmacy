package com.miaosha.dao;

import com.miaosha.dataobject.ProcessedMedicineTableDO;

public interface ProcessedMedicineTableDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processed_medicine_table
     *
     * @mbg.generated Tue Apr 16 16:36:29 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processed_medicine_table
     *
     * @mbg.generated Tue Apr 16 16:36:29 CST 2019
     */
    int insert(ProcessedMedicineTableDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processed_medicine_table
     *
     * @mbg.generated Tue Apr 16 16:36:29 CST 2019
     */
    int insertSelective(ProcessedMedicineTableDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processed_medicine_table
     *
     * @mbg.generated Tue Apr 16 16:36:29 CST 2019
     */
    ProcessedMedicineTableDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processed_medicine_table
     *
     * @mbg.generated Tue Apr 16 16:36:29 CST 2019
     */
    int updateByPrimaryKeySelective(ProcessedMedicineTableDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processed_medicine_table
     *
     * @mbg.generated Tue Apr 16 16:36:29 CST 2019
     */
    int updateByPrimaryKey(ProcessedMedicineTableDO record);
}