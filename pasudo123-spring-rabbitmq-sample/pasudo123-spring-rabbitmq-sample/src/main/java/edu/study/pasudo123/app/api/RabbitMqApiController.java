package edu.study.pasudo123.app.api;


import edu.study.pasudo123.app.model.Employee;
import edu.study.pasudo123.app.service.RabbitMqSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbit-mq")
@RequiredArgsConstructor
public class RabbitMqApiController {

    private final RabbitMqSender rabbitMqSender;
    private static final String MESSAGE_SEND_SUCCESS = "Message Sent Success !";


    @GetMapping(value = "/producer")
    public String producer(@RequestParam("empName") String empName,
                           @RequestParam("empId") String empId) {

        Employee emp = Employee
                .builder()
                .empName(empName)
                .empId(empId)
                .build();

        rabbitMqSender.send(emp);

        return MESSAGE_SEND_SUCCESS;
    }
}
