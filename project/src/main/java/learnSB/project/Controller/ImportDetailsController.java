package learnSB.project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImportDetailsController {

    @GetMapping("/api/import-details/{id}")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @PostMapping("/api/import-details")
    public String postMethodName(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

    @PutMapping("/api/import-details/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

    @DeleteMapping("/api/import-details/{id}")
    public String deleteMethodName(@PathVariable String id) {
        // TODO: process DELETE request

        return "Deleted";
    }
}
