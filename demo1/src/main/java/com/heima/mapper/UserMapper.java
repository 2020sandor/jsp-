package com.heima.mapper;

import com.heima.Domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    User select(@Param("username") String username,@Param("password") String password);

    @Insert("INSERT INTO `test`.`t_users`(`id`, `userName`, `password`) VALUES (null, #{username}, #{password});")
//    void add(@Param("username") String username,@Param("password") String password);
    void add(User user);

    List<User> selectAll();

    User selectbyus(@Param("username") String username);

    @Update("UPDATE `test`.`t_users` SET  `password` = #{password} WHERE `userName` = #{username};")
    void update(User user);
}
