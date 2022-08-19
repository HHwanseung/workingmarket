package working.workingmarket.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import working.workingmarket.entity.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
