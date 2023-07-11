package mate.application.port.out.mate;

import mate.domain.Mate;
import mate.domain.MatePost;

public interface ExistMatePort {

    Mate existMate(MatePost matePost, Long userId);

}