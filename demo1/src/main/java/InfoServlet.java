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

@WebServlet("/info")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String npassword = req.getParameter("npassword");

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//这3行是固定的

        SqlSession   sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user1 = userMapper.selectbyus(username);



        if(user1!=null&&user1.getPassword()!=npassword)
        {
//            System.out.println("注册中。。。");
            User newuser = new User(username,npassword);
            newuser.setUsername(username);
            newuser.setPassword(npassword);

//        userMapper.add(username,password);
            userMapper.update(newuser);
//            System.out.println("注册。。。");
            sqlSession.commit();
            sqlSession.close();
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write("密码修改成功！");

            writer.write("<br>");
            writer.write("<a href=\"index.jsp\"><input type=\"button\" value=\"返回主页\" >");


        }
        else
        {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write("修改失败！用户名不存在！或密码相同！");
            writer.write("<br>");
            writer.write("<a href=\"info.jsp\"><input type=\"button\" value=\"返回\" >");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
