package Project.Board.repository;

import Project.Board.dto.PostDto;
import Project.Board.entity.Post;
import Project.Board.mapper.Mapper;
import Project.Board.pagination.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final EntityManager em;
    private final Mapper mapper;

    @Override
    public Post save(PostDto dto) {
        Post post = mapper.postDtoToEntity(dto);
        em.persist(post);
        return post;
    }

    @Override
    public Post findById(Long Id) {
        Post findPost = em.find(Post.class, Id);
        return findPost;
    }

    @Override
    public List<Post> pagedFindAll(Pagination pagination) {
        return em.createQuery("select p from Post p order by p.postId asc", Post.class)
                .setFirstResult(pagination.limitStart)
                .setMaxResults(pagination.postCntPerPage)
                .getResultList();
    }

    @Override
    public int postCnt() {
        return em.createQuery("select p from Post p", Post.class).getResultList().size();
    }

    @Override
    public void update(Long id, PostDto updateParam) {
        Post oldPost = em.find(Post.class, id);
        mapper.postUpdate(oldPost, updateParam);
    }

    @Override
    public void delete(Long id) {
        Post post = em.find(Post.class, id);
        em.remove(post);
    }
}
