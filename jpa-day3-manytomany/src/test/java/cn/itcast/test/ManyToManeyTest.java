package cn.itcast.test;

import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManeyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Test
    @Transactional
    @Rollback(false)
    /**
     *保存一个用户，保存一个角色
     * 多对多放弃维护权，被动的一方放弃
     */

    public void testAdd(){
        User user=new User();
        user.setUserName("张三");
        Role role=new Role();
        role.setRoleName("java程序员");

        //配置用户到角色关系，可以对中间表的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户关系，可以对中间表的数据进行维护
        role.getUsers().add(user);
        userDao.save(user);
        roleDao.save(role);
    }
    @Test
    @Transactional
    @Rollback(false)
    /**
     *测试级联保存
     */
    public void testCasCadeAdd(){
        User user=new User();
        user.setUserName("张三");
        Role role=new Role();
        role.setRoleName("java程序员");

        //配置用户到角色关系，可以对中间表的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户关系，可以对中间表的数据进行维护
        role.getUsers().add(user);
        userDao.save(user);
    }
    @Test
    @Transactional
    @Rollback(false)
    /**
     *测试级联删除
     */
    public void testCasCadeRomove(){
        User user = userDao.findOne(1l);
        userDao.delete(user);
    }

}
