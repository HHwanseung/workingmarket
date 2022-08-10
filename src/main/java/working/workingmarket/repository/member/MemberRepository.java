package working.workingmarket.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import working.workingmarket.entity.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);

}
