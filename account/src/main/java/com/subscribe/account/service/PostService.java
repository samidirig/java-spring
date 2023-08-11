package com.subscribe.account.service;

import com.subscribe.account.dto.PostDto;
import com.subscribe.account.dto.converter.PostDtoConverter;
import com.subscribe.account.exception.UserNotFoundException;
import com.subscribe.account.model.Post;
import com.subscribe.account.model.User;
import com.subscribe.account.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostDtoConverter postDtoConverter;
    private final UserService userService;


    public PostService(PostRepository postRepository,
                       PostDtoConverter postDtoConverter,
                       UserService userService){
        this.postRepository = postRepository;
        this.postDtoConverter = postDtoConverter;
        this.userService = userService;
    }
    public PostDto createPost(PostDto newPost) {
        User user = userService.findUserById(newPost.getUserId());
        if (user==null) return null;

        Post post = new Post();
        post.setId(newPost.getId());
        post.setText(newPost.getText());
        post.setTitle(newPost.getTitle());
        post.setUser(user);

        return postDtoConverter.convertToPostDto(postRepository.save(post));
    }

    public List<PostDto> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll()
                .stream()
                .map(postDtoConverter::convertToPostDto)
                .collect(Collectors.toList());
    }

    protected Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User could not find by id: " + id));
    }
    public PostDto getPostById(Long postId) {
        return postDtoConverter.convertToPostDto(findPostById(postId));
    }

    public Post updatePost(Long postId, PostDto newPost){
        Post updatedPost = isPostExist(postId);
        updatedPost.setTitle(newPost.getTitle());
        updatedPost.setText(newPost.getText());
        return postRepository.save(updatedPost);
    }

    public Post deletePost(Long postId){
        Post post = isPostExist(postId);
        postRepository.delete(post);
        return post;
    }

    private Post isPostExist(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (Objects.isNull(post)){
            throw new UserNotFoundException("Post could not find by id: " + postId);
        }
        return post;
    }

}
