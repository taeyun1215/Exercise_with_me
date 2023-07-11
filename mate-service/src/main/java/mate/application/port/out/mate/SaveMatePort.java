package mate.application.port.out.mate;

import mate.domain.Mate;
import mate.domain.MatePost;

public interface SaveMatePort {

    void saveMate(Mate mate, MatePost matePost, Long userId);

}
