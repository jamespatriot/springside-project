package org.springside.examples.quickstart.repository;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springside.examples.quickstart.entity.Task;
import org.springside.examples.quickstart.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@ActiveProfiles("test")
public class UserDaoTest {


    @Autowired
    private UserDao userDao;

    @Test
    @Rollback(false)
    public void save() throws Exception {
        User user = new User();
        user.setLoginName("yang");
        user.setName("Yang.Liu");

        List<Task> taskList = new ArrayList<Task>();
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            Task task = new Task();
            task.setTitle("title_" + i);
            task.setDescription("description_" + i);
            task.setUser(user);
            taskList.add(task);

        }
        List<Task> taskList2 = new ArrayList<Task>();
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            Task task = new Task();
            task.setTitle("title2_" + i);
            task.setDescription("description2_" + i);
            task.setUser(user);
            taskList2.add(task);

        }
        user.setTasks(taskList);
//        user.test(taskList2);
        userDao.save(user);
        Iterator<Task> i = user.getTasks().iterator();
        for (Iterator<Task> it = user.getTasks().iterator(); it.hasNext(); ) {

            Task task = it.next();
            if (task.getTitle().equals("title_1")) {
                it.remove();
            }
        }


//        user.getTasks().clear();    A
//        user.setTasks(taskList2);
//        user.test(taskList);
        userDao.save(user);

//        Long num = Long.valueOf(userDao.countTaskNum(user.getId()).toString());

        Long num = Long.valueOf(userDao.countTaskNum2(Lists.newArrayList("title_1", "title_2", "title_3", "title_4")).toString());
        assertEquals(Long.valueOf(3), num);


    }

    @Test
    public void groupByRole() {
        List<Map<String, Object>> result = userDao.groupByRole();
        assertNotNull(result);
    }

    @Test
    public void testGetUsers() throws Exception {

        List<Object> users=userDao.getUsers(Lists.newArrayList(new Long[]{1L,2L}));
        assertNotNull(users);
    }
}