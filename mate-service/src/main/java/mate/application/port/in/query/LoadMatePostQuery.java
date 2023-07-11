package mate.application.port.in.query;

import mate.domain.MatePost;

public interface LoadMatePostQuery {

    MatePost loadMatePost(Long matePostId);

}
