package mate.application.service.matePost;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.application.port.in.query.LoadMatePostQuery;
import mate.application.port.in.usecase.ViewCountUpMatePostUseCase;
import mate.application.port.out.matePost.ViewCountUpMatePostStatePort;
import mate.domain.MatePost;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class ViewCountUpMatePostService implements ViewCountUpMatePostUseCase, LoadMatePostQuery {

    private final ViewCountUpMatePostStatePort viewCountUpMatePostStatePort;

    @Override
    public void viewCountUpMatePost(Long matePostId) {
        viewCountUpMatePostStatePort.viewCountUp(matePostId);
    }

    @Override
    public MatePost loadMatePost(Long matePostId) {
        return null;
    }
}
