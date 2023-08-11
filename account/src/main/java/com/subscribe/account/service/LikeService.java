package com.subscribe.account.service;

import com.subscribe.account.dto.LikeDto;
import com.subscribe.account.dto.converter.LikeDtoConverter;
import com.subscribe.account.exception.UserNotFoundException;
import com.subscribe.account.model.Like;
import com.subscribe.account.model.Post;
import com.subscribe.account.model.User;
import com.subscribe.account.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeDtoConverter likeDtoConverter;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository,
                       LikeDtoConverter likeDtoConverter,
                       UserService userService,
                       PostService postService){
        this.likeRepository = likeRepository;
        this.likeDtoConverter = likeDtoConverter;
        this.userService = userService;
        this.postService = postService;
    }

    public LikeDto createLike(LikeDto newLike) {
        User user = userService.findUserById(newLike.getUserId());
        Post post = postService.findPostById(newLike.getPostId());
        if(user == null && post == null) return null;

        Like like = new Like();
        like.setId(newLike.getId());
        like.setUser(user);
        like.setPost(post);

        return likeDtoConverter.convertToLikeDto(likeRepository.save(like));
    }

    public List<LikeDto> getAllLikes(Optional<Long> userId) {
        if (userId.isPresent()){
            return likeRepository.findByUserId(userId.get());
        }
        return likeRepository.findAll()
                .stream()
                .map(likeDtoConverter::convertToLikeDto)
                .collect(Collectors.toList());
    }

    protected Like findLikeById(Long likeId){
        return likeRepository.findById(likeId)
                .orElseThrow(()-> new UserNotFoundException("Like could not find by id: " + likeId));
    }

    public LikeDto getLikeById(Long likeId) {
        return likeDtoConverter.convertToLikeDto(findLikeById(likeId));
    }

    public Like deleteLike(Long likeId) {
        Like like = isLikeExist(likeId);
        likeRepository.delete(like);
        return like;
    }

    public Like isLikeExist(Long likeId){
        Like like = likeRepository.findById(likeId).orElse(null);
        if (Objects.isNull(like)){
            throw new UserNotFoundException("Like could not find by id: " + likeId);
        }
        return like;
    }
}
