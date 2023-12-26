package com.yoonmaYer.book.springboot.web;

import com.yoonmaYer.book.springboot.domain.posts.Posts;
import com.yoonmaYer.book.springboot.domain.posts.PostsRepository;
import com.yoonmaYer.book.springboot.service.PostsService;
import com.yoonmaYer.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }
}
