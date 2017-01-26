import de.holisticon.camunda.example.process.app.MyProcess;
import de.holisticon.camunda.example.process.delegate.DoSomethingDelegate;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.*;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.impl.VariableMapImpl;
import org.camunda.bpm.extension.needle.ProcessEngineNeedleRule;
import org.camunda.bpm.extension.needle.ProcessEngineNeedleRuleBuilder;
import org.junit.Rule;
import org.junit.Test;
import javax.inject.Inject;

import static de.holisticon.camunda.example.process.app.MyProcess.*;
import static de.holisticon.camunda.example.process.app.MyProcess.Elements.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.assertThat;
import static org.camunda.bpm.extension.mockito.DelegateExpressions.*;

public class MyFirstProcessTest {

    @Rule
    public ProcessEngineNeedleRule rule = new ProcessEngineNeedleRuleBuilder(this).build();

    @Inject
    RuntimeService runtimeService;

    @Test
    @Deployment(resources = RESOURCE)
    public void should_Deploy() {
        //...
    }

    @Test
    @Deployment(resources = RESOURCE)
    public void should_EndWithValidResult_whenDoSomethingReturnsValidResult() {

        registerJavaDelegateMock(DoSomethingDelegate.NAME).onExecutionSetVariables(Variables.putValue(MyProcess.Variables.VALID, true));

        ProcessInstance instance = runtimeService.startProcessInstanceByKey(KEY);

        assertThat(instance).hasPassedInOrder(START_EVENT, SERVICE_TASK_DO_SOMETHING, GATETEWAY_RESULT_VALID, END_EVENT_VALID);
        assertThat(instance).isEnded();

        verifyJavaDelegateMock(DoSomethingDelegate.NAME).executed();
    }

    @Test
    @Deployment(resources = RESOURCE)
    public void should_EndWithInvalidResult_whenDoSomethingReturnsInValidResult() {

        registerJavaDelegateMock(DoSomethingDelegate.NAME).onExecutionSetVariables(Variables.putValue(MyProcess.Variables.VALID, false));

        ProcessInstance instance = runtimeService.startProcessInstanceByKey(KEY);

        assertThat(instance).hasPassedInOrder(START_EVENT, SERVICE_TASK_DO_SOMETHING, GATETEWAY_RESULT_VALID, END_EVENT_INVALID);
        assertThat(instance).isEnded();
    }
}
