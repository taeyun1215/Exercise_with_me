package dev.ewm.domain.matePost;

import dev.ewm.domain.matePost.request.MatePostCreateRequest;
import dev.ewm.domain.user.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface MatePostService {

    // DB 운동 메이트 게시글 저장
    MatePost createMatePost(MatePostCreateRequest matePostCreateRequest, User user);

}
