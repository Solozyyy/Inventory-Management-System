package learnSB.project.Controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import learnSB.project.Domain.Material;
import learnSB.project.Service.MaterialService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/api/material/{id}")
    public ResponseEntity<?> getMaterial(@PathVariable int id) {
        Material material = materialService.getMaterialById(id);
        if (material == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(material);
    }

    @GetMapping("/api/material")
    public ResponseEntity<Slice<Material>> getAllMaterial(
            @RequestParam(defaultValue = "") String name,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<Material> materials = materialService.findByNameContaining(name, pageable);
        // Page<Material> materials = materialRepository.findByNameContaining(name,
        // pageable);
        return ResponseEntity.ok(materials);
    }

    @PostMapping("/api/material")
    public ResponseEntity<String> createMaterial(@RequestBody Material material) {
        materialService.saveMaterial(material);
        return ResponseEntity.ok("create successfully");
    }

    // @PutMapping("/api/material/{id}")
    // public ResponseEntity<String> editMaterial(@PathVariable int id, @RequestBody
    // Material material) {
    // Material m = materialRepository.findById(id);
    // if (material == null) {
    // return ResponseEntity.notFound().build();
    // }
    // m.setName(material.getName());
    // m.setQuantity(material.getQuantity());
    // m.setPrice(material.getPrice());
    // materialRepository.save(material);
    // return ResponseEntity.ok("edit successfully");
    // }

    @DeleteMapping("/api/material/{id}")
    public ResponseEntity<String> deleteMaterial(@PathVariable int id) {
        Material material = materialService.getMaterialById(id);
        if (material == null) {
            return ResponseEntity.notFound().build();
        }
        materialService.deleteMaterial(material);
        return ResponseEntity.ok("delete successfully");
    }

}
