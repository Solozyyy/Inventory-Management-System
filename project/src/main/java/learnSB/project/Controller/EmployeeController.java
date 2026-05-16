package learnSB.project.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class EmployeeController {

    @GetMapping("/api/employee/dashboard")
    public String employeeDashboard() {
        return "Employee Dashboard";
    }

}
