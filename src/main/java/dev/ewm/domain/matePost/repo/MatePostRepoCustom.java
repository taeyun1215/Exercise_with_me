package dev.ewm.domain.matePost.repo;

import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.matePost.request.MatePostSearchRequireRequest;

import java.util.List;

public interface MatePostRepoCustom {

    List<MatePost> searchAll(MatePostSearchRequireRequest matePostSearchRequireRequest);

}
