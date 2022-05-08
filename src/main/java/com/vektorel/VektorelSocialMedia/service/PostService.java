package com.vektorel.VektorelSocialMedia.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.vektorel.VektorelSocialMedia.dto.response.PagePostDto;
import com.vektorel.VektorelSocialMedia.repository.CommentRepository;
import com.vektorel.VektorelSocialMedia.repository.DislikeToPostRepository;
import com.vektorel.VektorelSocialMedia.repository.LikeToPostRepository;
import com.vektorel.VektorelSocialMedia.repository.PostRepository;
import com.vektorel.VektorelSocialMedia.repository.entity.DislikeToPost;
import com.vektorel.VektorelSocialMedia.repository.entity.Follower;
import com.vektorel.VektorelSocialMedia.repository.entity.LikeToPost;
import com.vektorel.VektorelSocialMedia.repository.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${myproject.google.storage.bucketname}")
    private String bucketname;

    @Getter
    @Autowired
    Storage storage;

    private final PostRepository repository;
    private final CommentRepository commentRepository;
    private final LikeToPostRepository likeToPostRepository;
    private final DislikeToPostRepository dislikeToPostRepository;
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
            post.setUrl(getGoogleSignedMediaPath(post.getUrl(),10).get().toString());
            Optional<LikeToPost> likeToPost =
                    likeToPostRepository.findOptionalByPostIdAndUserId(post.getId(),id);
            if(likeToPost.isPresent())
                post.setIslike(true);
            Optional<DislikeToPost> dislikeToPost =
                    dislikeToPostRepository.findOptionalByPostIdAndUserId(post.getId(),id);
            if(dislikeToPost.isPresent())
                post.setIsdislike(true);
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

    public Optional<String> uploadFile(MultipartFile file){
            try {
                String mediaUrl;
                if(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")){
                    mediaUrl = UUID.randomUUID().toString()+".png";
                }else
                    mediaUrl = UUID.randomUUID().toString()+".mp4";
                BlobInfo blobInfo = BlobInfo.newBuilder(bucketname, mediaUrl).build();
                storage.create(blobInfo, file.getBytes());
                return Optional.of(mediaUrl);
            }catch (Exception e){
                return Optional.empty();
            }
    }

    /**
     * Bağlanılmakistenilen storage bilgilerini buragiriyoruz.
     * @param bucketname
     * @param subdirectory
     * @param filename
     * @return
     */
    private BlobId constructBlobId(String bucketname, String subdirectory,
                                   String filename) {
        return Optional.ofNullable(subdirectory)
                .map(s -> BlobId.of(bucketname, subdirectory + "/" + filename))
                .orElse(BlobId.of(bucketname, filename));
    }

    public Optional<URL> getGoogleSignedMediaPath(String mediaName, int minutes) {
        BlobId blobId = constructBlobId(bucketname, null, mediaName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        return createSignedPathStyleUrl(blobInfo, minutes, TimeUnit.MINUTES);
    }

    private Optional<URL> createSignedPathStyleUrl(BlobInfo blobInfo,
                                                   int duration, TimeUnit unit) {
        return Optional.of(getStorage()
                .signUrl(blobInfo,duration,unit,Storage.SignUrlOption.withPathStyle()));

    }

}
