package de.holisticon.camunda.example.process.app;

public class MyProcess {

    public static final String KEY = "process_myFirstProcess";
    public static final String RESOURCE = "my_process.bpmn";

    public enum Variables {
        ;
        public static final String VALID = "valid";
    }

    public enum Elements {
        ;
        public static final String START_EVENT = "start_event_my_process";
        public static final String SERVICE_TASK_DO_SOMETHING = "service_task_do_something";
        public static final String GATETEWAY_RESULT_VALID = "gateway_result_valid";
        public static final String END_EVENT_VALID = "end_event_my_process_valid";
        public static final String END_EVENT_INVALID = "end_event_my_process_invalid";
    }
}
