package learnSB.project.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learnSB.project.Domain.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findById(int id);

    Slice<Supplier> findByNameContaining(String name, Pageable pageable);

    // ArrayList<Supplier> findByAddress(String address);

    // ArrayList<Supplier> findByPhone(String phone);

    // ArrayList<Supplier> findByEmail(String email);
}
