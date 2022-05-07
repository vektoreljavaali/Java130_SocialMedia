package com.vektorel.VektorelSocialMedia.service;

import com.vektorel.VektorelSocialMedia.dto.response.PagePostDto;
import com.vektorel.VektorelSocialMedia.repository.CommentRepository;
import com.vektorel.VektorelSocialMedia.repository.PostRepository;
import com.vektorel.VektorelSocialMedia.repository.entity.Follower;
import com.vektorel.VektorelSocialMedia.repository.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final CommentRepository commentRepository;

    public void save(Post item){
        repository.save(item);
    }
    public void update(Post item){
        repository.save(item);
    }
    public void delete(long id){
        repository.deleteById(id);
    }
    public List<Post> findAll(){
        return repository.findAll();
    }
    public List<PagePostDto> findByUserid(long id){
        List<PagePostDto> pagePostDtos = new ArrayList<>();
        List<Post> postList = repository.findByUseridOrderBySharedtimeDesc(id);
        for (Post post: postList) {
            PagePostDto dto = new PagePostDto();
            dto.setPost(post);
            dto.setCommentList(commentRepository.findByPostidOrderByCreatetimeDesc(post.getId()));
            pagePostDtos.add(dto);
        }
        return pagePostDtos;
    }
    /**
     * Optional -> içerisinde bulunan varlığın değerinin analizini ve
     * kullanacaklar için tutarlılığını kontrol eder. bazen kullanmakta
     * olduğumuz nesneler null gelirse uygulamamız çalışma zamanında
     * bunu tolere edemez ve uygulama down olur. bu istemediğimiz bir durumdur.
     * bu nedenle optional kullanmak faydalıdır.
     * @param id
     * @return
     */
    public Optional<Post> findById(long id){
        return repository.findById(id);
    }
}
