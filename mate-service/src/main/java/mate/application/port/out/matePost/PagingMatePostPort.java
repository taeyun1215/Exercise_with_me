package mate.application.port.out.matePost;

import mate.domain.MatePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagingMatePostPort {

    Page<MatePost> pageMatePostList(Pageable pageable);

}
