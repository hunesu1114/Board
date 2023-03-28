package Project.Board.repository;

import Project.Board.dto.PostDto;
import Project.Board.entity.Post;
import Project.Board.mapper.Mapper;
import Project.Board.pagination.Pagination;
import Project.Board.pagination.PagingConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final EntityManager em;
    private final Mapper mapper;

    @Override
    public Post save(PostDto dto, HttpServletRequest request) {
        Post post = mapper.postSave(dto,request);
        em.persist(post);
        return post;
    }

    @Override
    public Post findById(Long Id) {
        Post findPost = em.find(Post.class, Id);
        return findPost;
    }

    @Override
    public List<Post> pagedFindAll(int page) {
        return em.createQuery("select p from Post p order by p.postId asc", Post.class)
                .setFirstResult(PagingConst.POST_CNT_PER_PAGE*(page-1))
                .setMaxResults(PagingConst.POST_CNT_PER_PAGE)
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

    //initData용
    @Override
    public void initSave(Post post) {
        em.persist(post);
    }
}
