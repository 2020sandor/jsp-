import com.heima.Domain.User;
import com.heima.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String username = req.getParameter("username");
       String password = req.getParameter("password");

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//这3行是固定的

        SqlSession   sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user1 = userMapper.selectbyus(username);



    if(user1==null)
    {
        System.out.println("注册中。。。");
        User newuser = new User(username,password);
        newuser.setUsername(username);
        newuser.setPassword(password);

//        userMapper.add(username,password);
        userMapper.add(newuser);
        System.out.println("注册。。。");
        sqlSession.commit();
        sqlSession.close();
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();

        writer.write("注册成功！请登陆！");
        writer.write("<br>");
        writer.write("<a href=\"login.jsp\"><input type=\"button\" value=\"登陆\" >");


    }
    else
    {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("注册失败！用户名已存在！");
        writer.write("<br>");
        writer.write("<a href=\"login.jsp\"><input type=\"button\" value=\"返回\" >");
    }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
