package com.vektorel.VektorelSocialMedia.service;

import com.vektorel.VektorelSocialMedia.repository.PostRepository;
import com.vektorel.VektorelSocialMedia.repository.entity.Follower;
import com.vektorel.VektorelSocialMedia.repository.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
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
    public List<Post> findByUserid(long id){
        return repository.findByUseridOrderBySharedtimeDesc(id);
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
