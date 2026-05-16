package learnSB.project.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learnSB.project.Domain.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    Material findById(int id);

    Slice<Material> findByNameContaining(String name, Pageable page);
    // Page<Material> findByNameContaining(String name, Pageable page);
}
