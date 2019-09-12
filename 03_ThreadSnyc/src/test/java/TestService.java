import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author skylaker2019@163.com
 * @version V1.0 2019/8/19 9:53 PM
 */
public class TestService {
    static Object object = new Object();
    public static void main(String[] args) {
        Map map = Collections.synchronizedMap(new HashMap());

    }

    public void sysMethod(){
        synchronized(object){
            doWork1();
            doWork2();
        }
    }

    private void doWork2() {
    }

    private void mutexMethod() {
    }

    private void doWork1() {
    }
}
