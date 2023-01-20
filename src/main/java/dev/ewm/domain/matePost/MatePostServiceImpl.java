package dev.ewm.domain.matePost;

import dev.ewm.domain.mate.MateRepo;
import dev.ewm.domain.matePost.request.MatePostCreateRequest;
import dev.ewm.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MatePostServiceImpl implements MatePostService {

    private final MatePostRepo matePostRepo;
    private final MateRepo mateRepo;

    @Override
    @Transactional
    public MatePost createMatePost(MatePostCreateRequest matePostCreateRequest, String username) {
//        MatePost matePost = matePostCreateRequest.toEntity(user);
//        matePostRepo.save(matePost);
//        log.info("운동 메이트 게시글이 추가됐습니다. : ", matePost.getTitle());

//        return matePost;
        return null;
    }
}
