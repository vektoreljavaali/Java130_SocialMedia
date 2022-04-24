package com.vektorel.VektorelSocialMedia.service;

import com.vektorel.VektorelSocialMedia.repository.TagsRepository;
import com.vektorel.VektorelSocialMedia.repository.entity.Follower;
import com.vektorel.VektorelSocialMedia.repository.entity.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagsRepository repository;
    public void save(Tags item){
        repository.save(item);
    }
    public void update(Tags item){
        repository.save(item);
    }
    public void delete(long id){
        repository.deleteById(id);
    }
    public List<Tags> findAll(){
        return repository.findAll();
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
    public Optional<Tags> findById(long id){
        return repository.findById(id);
    }
}
