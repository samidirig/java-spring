package com.subscribe.account.controller;

import com.subscribe.account.dto.PostDto;
import com.subscribe.account.model.Post;
import com.subscribe.account.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam Optional<Long> userId){
        return ResponseEntity.ok(postService.getAllPosts(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto newPost) {
        return ResponseEntity.ok(postService.createPost(newPost));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PutMapping("/edit/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") Long postId, @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePost(postId, postDto));
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(postService.deletePost(postId));
    }


}
