package learnSB.project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {

    @GetMapping("/api/hello")
    @ResponseBody
    public String greet() {
        return "con cặc";
    }
}
