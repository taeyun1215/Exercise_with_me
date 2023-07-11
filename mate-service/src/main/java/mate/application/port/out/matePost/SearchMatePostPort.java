package mate.application.port.out.matePost;

import mate.domain.MatePost;

import java.util.List;

public interface SearchMatePostPort {

    List<MatePost> searchAll(MatePost matePost);

}