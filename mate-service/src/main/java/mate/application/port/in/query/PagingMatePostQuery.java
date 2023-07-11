package mate.application.port.in.query;

import mate.domain.MatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagingMatePostQuery {

    Page<MatePost> pageMatePostList(Pageable pageable);

}
