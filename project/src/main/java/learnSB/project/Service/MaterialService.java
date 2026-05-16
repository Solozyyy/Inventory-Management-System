package learnSB.project.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import learnSB.project.Domain.Material;
import learnSB.project.Repository.MaterialRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    public Slice<Material> findByNameContaining(String name, Pageable pageable) {
        return materialRepository.findByNameContaining(name, pageable);
    }

    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    public void deleteMaterial(Material material) {
        materialRepository.delete(material);
    }

    public Material getMaterialById(int id) {
        return materialRepository.findById(id);
    }
}
