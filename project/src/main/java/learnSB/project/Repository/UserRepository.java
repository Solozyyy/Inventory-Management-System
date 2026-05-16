package learnSB.project.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import learnSB.project.Domain.Member;

@Repository
public interface UserRepository extends JpaRepository<Member, Integer> {

    Member findById(int id);

    ArrayList<Member> findAll();

    Member findByUserName(String userName);

}
