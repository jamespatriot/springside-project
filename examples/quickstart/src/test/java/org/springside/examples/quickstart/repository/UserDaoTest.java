package org.springside.examples.quickstart.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springside.examples.quickstart.entity.Task;
import org.springside.examples.quickstart.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@ActiveProfiles("development")
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
        user.setTasks(taskList);
        userDao.save(user);

//        Long num = userDao.countTaskNum(user.getId());
//        assertEquals(Long.valueOf(5), num);
    }
}