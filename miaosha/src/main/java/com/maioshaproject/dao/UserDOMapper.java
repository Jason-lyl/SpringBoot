package com.maioshaproject.dao;

import com.maioshaproject.dataObject.UserDO;
import org.springframework.stereotype.Service;

@Service
public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Aug 13 17:10:50 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Aug 13 17:10:50 CST 2019
     */
    int insert(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Aug 13 17:10:50 CST 2019
     */
    int insertSelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Aug 13 17:10:50 CST 2019
     */
    UserDO selectByPrimaryKey(Integer id);
    UserDO selectByTelphone(String telphone);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Aug 13 17:10:50 CST 2019
     */
    int updateByPrimaryKeySelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Aug 13 17:10:50 CST 2019
     */
    int updateByPrimaryKey(UserDO record);
}