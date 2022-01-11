import com.zxz.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MyTest
 * @Description TODO
 * @Creat 2021-11-18  10:37:51
 */
public class MyTest {

    /*
     find all
     */
    @Test
    public void testFindAll() throws IOException {
        // 1.加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.执行sql
        List<User> users = sqlSession.selectList("user.findAll");
        // 5.打印结果
        for (User user : users) {
            System.out.println(user);
        }
        // 6.释放资源
        sqlSession.close();
    }

    /*
     save  user
     */
    @Test
    public void testSaveUser() throws IOException {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsername("Tom");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("London");

        sqlSession.insert("user.saveUser",user);

        // insert 操作的时候需要提交事务
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }
    /*
     update  user
     */
    @Test
    public void testUpdateUser() throws IOException {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(4);
        user.setUsername("Jerry");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("en-London");

        sqlSession.update("user.updateUser",user);

        // insert 操作的时候需要提交事务
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }

    /*
     delete  user
     */
    @Test
    public void testDeleteUser() throws IOException {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("user.deleteUser",4);

        // insert 操作的时候需要提交事务
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }


}
