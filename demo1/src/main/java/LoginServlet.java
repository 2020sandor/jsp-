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
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String username = req.getParameter("username");
       String password = req.getParameter("password");

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//这3行是固定的

        SqlSession   sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.select(username,password);
        sqlSession.close();


        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();

    if(user!=null)
    {
        writer.write("<h1>登陆成功！</h1>");
        writer.write("<br>");
        writer.write("<a href=\"info.jsp\"><input type=\"button\" value=\"修改信息\" >");

    }else
    {
        writer.write("<h1>登陆失败！</h1>");
        writer.write("<br>");
        writer.write("<a href=\"login.jsp\"><input type=\"button\" value=\"返回\" >");
    }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
