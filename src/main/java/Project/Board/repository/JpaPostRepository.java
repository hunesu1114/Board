package Project.Board.repository;

import Project.Board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<Post,Long> {

    public List<Post> findByTitleContaining(String keyword);

    public List<Post> findByContentContaining(String keyword);
}
