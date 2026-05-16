package learnSB.project.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import learnSB.project.Domain.Supplier;

@Controller
public class SupplierController {

    @GetMapping("/api/supplier/{id}")
    public ResponseEntity<String> getSupplier(@PathVariable int id) {
        return ResponseEntity.ok("get successfully");
    }

    @PostMapping("/api/supplier")
    public ResponseEntity<String> createSupplier(@RequestBody Supplier supplier) {
        return ResponseEntity.ok("create successfully");
    }

    @PutMapping("/api/supplier/{id}")
    public ResponseEntity<String> editSupplier() {
        return ResponseEntity.ok("edit successfully");
    }

    @DeleteMapping("/api/supplier/{id}")
    public ResponseEntity<String> deleteSupplier() {
        return ResponseEntity.ok("delete successfully");
    }

}
