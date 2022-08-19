package working.workingmarket.service.comment;

import working.workingmarket.entity.comment.Comment;

import java.util.List;

public interface CommentService {

    void save(Comment comment);

    Comment findById(Long id) throws Exception;


    List<Comment> findAll();

    void remove(Long id) throws Exception;
}