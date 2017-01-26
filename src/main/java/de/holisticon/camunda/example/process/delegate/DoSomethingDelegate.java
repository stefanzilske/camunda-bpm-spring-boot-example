package de.holisticon.camunda.example.process.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;


@Component
public class DoSomethingDelegate implements JavaDelegate {

    public static final String NAME = "doSomethingDelegate";


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // did something
    }
}
