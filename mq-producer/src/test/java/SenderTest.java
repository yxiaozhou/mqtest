import com.tim.producer.ProducerApplication;
import com.tim.producer.sender.Sender1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

@SpringBootTest(classes = ProducerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SenderTest {
    @Autowired
    private Sender1 sender;
    @Test
    public void testRabbit1() {
        for (int i = 0; i < 100000; i++) {
            sender.sendHello(i);
        }
    }
    @Test
    public void testRabbit2() {
        sender.sendHello2();
    }
    @Test
    public void testRabbit3() {
        sender.sendTopic();
    }
    @Test
    public void testRabbit4() {
        sender.sendTopic2();
    }
    @Test
    public void testDelay() throws InterruptedException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        sender.sendDelay("Hi Admin.");
        System.out.println("send over"+System.currentTimeMillis());
        Thread.sleep(5 * 1000); //等待接收程序执行之后，再退出测试
    }
    @Test
    public void testRabbit5() {
        sender.sendMultiple();
    }
}
