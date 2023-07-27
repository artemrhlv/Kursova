package awaitility;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionEvaluationLogger;
import org.awaitility.core.ThrowingRunnable;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Durations.ONE_SECOND;
import static org.testng.Assert.assertEquals;

@Slf4j
public class GithubWorkFlowRunTest {
/*
jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
    - name: Sleep for 30 seconds
      uses: jakejarvis/wait-action@master
      with:
        time: '30s'
*/

    @Test
    public void getWorkflowInfo() {
        waitUntilCondition(5, () -> assertEquals(getWorkflowInfo("5478076614")
                .getStatus(), "completed"));
    }

    public void waitUntilCondition(Integer atMost, ThrowingRunnable condition) {
        Awaitility.await()
                .with()
                .conditionEvaluationListener(new ConditionEvaluationLogger(log::info))
                .atMost(atMost, TimeUnit.SECONDS)
//                .during(ONE_SECOND)
                .pollInterval(ONE_SECOND)
                .untilAsserted(condition);
    }

    public GithubWorkflowResponse getWorkflowInfo(String id) {
        Response response = RestAssured.given()
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer {TOKEN}") //it's better do not use token directly, save it in the secret place)
                .get("https://api.github.com/repos/{owner}/{repo}/actions/runs/{RUN_ID}", id);
        response.prettyPrint();
        return response.as(GithubWorkflowResponse.class);
    }

}
