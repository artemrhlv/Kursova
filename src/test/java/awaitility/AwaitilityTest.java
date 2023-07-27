package awaitility;

import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.awaitility.core.ConditionEvaluationLogger;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class AwaitilityTest {

    private int counter = 0;

    public int getCounter() {
        System.out.println(counter++);
        return counter;
    }

    public boolean isNumberGreaterThan(int expectedNum) {
        System.out.println(counter++);
        return counter > expectedNum;
    }

    @Test
    public void checkAwaitility(){
        Awaitility.await("wait until number will be greater than 5")
                .with()
                .conditionEvaluationListener(new ConditionEvaluationLogger(log::info))
                .atMost(Durations.TWO_SECONDS)
                .pollDelay(500, TimeUnit.MILLISECONDS)
                .pollInterval(Durations.ONE_SECOND)
//                .until(()->isNumberGreaterThan(5));
                .until(()->getCounter(), Matchers.greaterThanOrEqualTo(5));

        System.out.println("Done");
    }
}