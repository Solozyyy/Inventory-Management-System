package learnSB.project.Service;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import learnSB.project.Domain.Supplier;
import learnSB.project.Repository.SupplierRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public boolean saveSupplier(Supplier supplier) {
        if (supplier != null) {
            supplierRepository.save(supplier);
            return true;
        } else {
            return false;
        }
    }

    public Slice<Supplier> findByNameContaining(String name, Pageable pageable) {
        return supplierRepository.findByNameContaining(name, pageable);
    }

    public Supplier findById(int id) {
        return supplierRepository.findById(id);
    }

}
