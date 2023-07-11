package mate.application.service.matePost;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.application.port.in.query.PagingMatePostQuery;
import mate.application.port.out.matePost.PagingMatePostPort;
import mate.domain.MatePost;
import mate.global.annotation.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class PagingMatePostService implements PagingMatePostQuery {

    private final PagingMatePostPort pagingMatePostPort;

    @Override
    public Page<MatePost> pageMatePostList(Pageable pageable) {
        return pagingMatePostPort.pageMatePostList(pageable);
    }

}
