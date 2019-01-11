import com.example.WebFluxApplication;
import com.example.redis.RedisDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunyong on 2019/1/5.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebFluxApplication.class)
public class DemoTest {

//    @Autowired
//    private RedisDemo redisDemo;
//
//    @Test
//    public void test1() {
////        redisDemo.testZSet();
////        redisDemo.testHash();
//        redisDemo.testList();
//    }

    @Test
    public void testa() {
        int[] a = {1, 3, 5, 7, 9, 11};
        int[] b = {2, 4, 6, 8, 9};
        int[] merge = merge(a, b);
        System.out.println(merge);
    }

    public static int[] merge(int[] a, int[] b) {

        int[] answer = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length)
            answer[k++] = a[i] < b[j] ? a[i++] : b[j++];

        while (i < a.length)
            answer[k++] = a[i++];


        while (j < b.length)
            answer[k++] = b[j++];

        return answer;
    }
}
