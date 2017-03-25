package cn.sunxyz;

import cn.sunxyz.domain.User;
import cn.sunxyz.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootMybatisApplicationTests.class);

	@Autowired
	private UserMapper userMapper;

	@Before
	public void before(){
		for(int i=0;i<3;i++){
			User user = new User();
			user.setName("user"+i);
			user.setPassword("user"+i);
//			userMapper.insert(user);
			userMapper.insertUserXml(user);
		}
	}

	@Test
	public void contextLoads() {
		PageHelper.startPage(1,2);//无法与 注解生成动态sql同步使用
		List<User> users = userMapper.fingAll();
//		List<User> users = userMapper.findByNameLike("user");
		logger.info(users.toString());
	}

}
