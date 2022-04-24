package com.vektorel.VektorelSocialMedia.service;

import com.vektorel.VektorelSocialMedia.repository.CommentRepository;
import com.vektorel.VektorelSocialMedia.repository.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/**
 * Lombok içinde bnulunan bu annotasyon ile zorunlu olarak oluşturulmasu
 * gerekli olan constructor ların lombok trarafından yazılması sağlanır.
 * sınıf içinde final olarak tanımlanan değişkenler için geçerlidir.
 */
@RequiredArgsConstructor
public class CommentService {
    /**
     * DİKKAT!!!
     * Spring nesneleri compilertime da kendisi rast gele üretmek üzere
     * @Autowired annotasyonu kullanır. ancak nesne tutarlılığı ve günveliği
     * açısından, nesnelerin final kullanırak değişmezliğinin garanti edilmesi
     * ve constructor içerisinde nesne yaratılması önerilir.
     */
    private final CommentRepository repository;

    public void save(Comment item){
        repository.save(item);
    }
    public void update(Comment item){
        repository.save(item);
    }
    public void delete(long id){
        repository.deleteById(id);
    }
    public List<Comment> findAll(){
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
    public Optional<Comment> findById(long id){
        return repository.findById(id);
    }

}
