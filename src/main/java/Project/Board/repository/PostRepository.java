package Project.Board.repository;

import Project.Board.dto.PostDto;
import Project.Board.entity.Post;
import Project.Board.pagination.Pagination;

import java.util.List;

public interface PostRepository {

    public Post save(PostDto dto);

    public Post findById(Long id);

    public List<Post> pagedFindAll(Pagination pagination);

    public int postCnt();

    public void update(Long id, PostDto updateParam);

    public void delete(Long id);

}
