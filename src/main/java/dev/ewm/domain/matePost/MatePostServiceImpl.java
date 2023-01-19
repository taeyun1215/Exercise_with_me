package dev.ewm.domain.matePost;

import dev.ewm.domain.matePost.request.MatePostCreateRequest;
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

    @Override
    @Transactional
    public MatePost createMatePost(MatePostCreateRequest matePostCreateRequest) {

    }
}
