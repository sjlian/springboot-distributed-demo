package test.lian;

import com.lian.AppApplication;
import com.lian.common.response.MyException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserServiceTest {
    @BeforeClass
    public void beforeClass(){
        System.out.println("before class");
    }

    @Before
    public void initData(){
        System.out.println("before test.I will init data");
    }

    @Test(timeout = 1000,expected = MyException.class)
    public void test(){
        System.out.println("the test method");
    }

    @After
    public void delData(){
        System.out.println("after test.I will delete data");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("after class");
    }


    /**
     * 系统在测试时会自动忽略掉该注解修饰方法
     */
    @Ignore("not ready yet")
    public void notReadyYet(){
        int i= 3 / 0;
    }


}
