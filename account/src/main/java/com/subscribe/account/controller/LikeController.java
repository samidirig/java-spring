package com.subscribe.account.controller;

import com.subscribe.account.dto.LikeDto;
import com.subscribe.account.model.Like;
import com.subscribe.account.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public ResponseEntity<List<LikeDto>> getAllLikes(@RequestParam Optional<Long> userId){
        return ResponseEntity.ok(likeService.getAllLikes(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<LikeDto> createLike(@RequestBody LikeDto newLike) {
        return ResponseEntity.ok(likeService.createLike(newLike));
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<LikeDto> getLikeById(@PathVariable("likeId") Long likeId){
        return ResponseEntity.ok(likeService.getLikeById(likeId));
    }

//    @PutMapping("/edit/{likeId}")
//    public ResponseEntity<Like> updateLike(@PathVariable("likeId") Long likeId, @RequestBody LikeDto likeDto){
//        return ResponseEntity.ok(likeService.updateLike(likeId, likeDto));
//    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Like> deleteLike(@PathVariable("likeId") Long likeId){
        return ResponseEntity.ok(likeService.deleteLike(likeId));
    }

}
