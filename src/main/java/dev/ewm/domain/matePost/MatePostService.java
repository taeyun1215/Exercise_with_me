package dev.ewm.domain.matePost;

import dev.ewm.domain.mate.Mate;
import dev.ewm.domain.matePost.request.MatePostCreateRequest;
import dev.ewm.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface MatePostService {

    // 운동 메이트 게시글 페이징
    Page<MatePost> pageMatePostList(Pageable pageable);

    // DB 운동 메이트 게시글 저장
    MatePost createMatePost(MatePostCreateRequest matePostCreateRequest, User user);

    // 운동 메이트 조인
    List<Mate> joinMate(Long matePostId, User user);

}
