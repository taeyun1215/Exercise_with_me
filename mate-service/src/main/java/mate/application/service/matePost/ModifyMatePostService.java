package mate.application.service.matePost;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.adapter.in.request.ModifyMatePostRequest;
import mate.application.port.in.query.LoadMatePostQuery;
import mate.application.port.in.usecase.ModifyMatePostUseCase;
import mate.application.port.out.matePost.SaveMatePostPort;
import mate.domain.MatePost;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMatePostService implements ModifyMatePostUseCase {

    private final LoadMatePostQuery loadMatePostQuery;
    private final SaveMatePostPort saveMatePostPort;

    @Override
    public void modifyMatePost(
            ModifyMatePostRequest modifyMatePostRequest,
            Long matePostId,
            Long userId
    ) {
        MatePost matePost = loadMatePostQuery.loadMatePost(matePostId);
        matePost.updateMatePost(modifyMatePostRequest);
        saveMatePostPort.saveMatePost(matePost);
    }

}