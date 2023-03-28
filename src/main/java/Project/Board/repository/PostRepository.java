package Project.Board.repository;

import Project.Board.dto.PostDto;
import Project.Board.entity.Post;
import Project.Board.pagination.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PostRepository {

    public Post save(PostDto dto, HttpServletRequest request);

    public Post findById(Long id);

    public List<Post> pagedFindAll(int page);

    public int postCnt();

    public void update(Long id, PostDto updateParam);

    public void delete(Long id);

    public void initSave(Post post);
}
