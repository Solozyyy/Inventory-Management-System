package learnSB.project.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ImportInvoiceController {

    @GetMapping("/api/import-invoice/{id}")
    public ResponseEntity<String> getImportInvoice(@PathVariable Long id) {
        return ResponseEntity.ok("get successfully");
    }

    @PostMapping("/api/import-invoice")
    public ResponseEntity<String> createImportInvoice() {
        return ResponseEntity.ok("create successfully");
    }

    @PutMapping("/api/import-invoice/{id}")
    public ResponseEntity<String> editImportInvoice(@PathVariable Long id) {
        return ResponseEntity.ok("edit successfully");
    }

    @DeleteMapping("/api/import-invoice/{id}")
    public ResponseEntity<String> deleteImportInvoice(@PathVariable Long id) {
        return ResponseEntity.ok("delete successfully");
    }

}
