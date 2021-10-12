package com.hogwarts.sns.application;

import com.hogwarts.sns.domain.PostIndex;
import com.hogwarts.sns.presentation.request.CreatePostRequest;
import com.hogwarts.sns.presentation.request.PostSearchRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class PostServiceTest {

    Logger log = LoggerFactory.getLogger(PostServiceTest.class);

    @Autowired
    PostService postService;

    @Test
    @DisplayName("부분 검색 테스트")
    void search() {
        List<MultipartFile> images = new ArrayList<>();

        List<CreatePostRequest> requests = List.of(
                new CreatePostRequest("나 우리", images),
                new CreatePostRequest("나의 우리나라", images),
                new CreatePostRequest("우주의 이야기", images),
                new CreatePostRequest("우유의 이야기", images),
                new CreatePostRequest("우기의 사랑", images),
                new CreatePostRequest("우리 은행", images),
                new CreatePostRequest("우리", images),
                new CreatePostRequest("우리나라", images),
                new CreatePostRequest("우주", images),
                new CreatePostRequest("우주의 나라", images)
        );

        for (CreatePostRequest p : requests) {
            postService.create(null, p);
        }

        PageRequest pageRequest = PageRequest.of(0, 5);
        PostSearchRequest request = new PostSearchRequest("우리");

        List<PostIndex> index = postService.getAllIndex(request, pageRequest);

        for (PostIndex i : index) {
            log.info(i.toString());
        }

    }

}