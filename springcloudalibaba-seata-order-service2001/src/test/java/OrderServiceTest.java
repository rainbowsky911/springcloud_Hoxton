import com.wsh.springcloud.alibaba.SpringCloudAlibabaSeataOrderServiceApplication2001;
import com.wsh.springcloud.alibaba.feign.AccountFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zdw
 * @Date: 2021/05/08/11:41
 * @Description:
 */
@SpringBootTest(classes = SpringCloudAlibabaSeataOrderServiceApplication2001.class)
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    private AccountFeignClient accountFeignClient;
    @Test

    public void  getAccount(){
        Object account = accountFeignClient.getAccount(1L);
        System.out.println(account);
    }
}
