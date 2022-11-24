package com.heima.service;

import com.heima.Domain.User;
import com.heima.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//这3行是固定的


//        ""
        //获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        List<User> users = sqlSession.selectList("test1.selectAll");
        UserMapper userMapper =sqlSession.getMapper(UserMapper.class);

        System.out.println(userMapper.selectAll());
        sqlSession.close();
    }
}
