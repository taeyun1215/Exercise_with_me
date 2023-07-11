package mate.adapter.out.persistence.matePost;

import java.util.List;

public interface MatePostJpaRepoCustom {

    List<MatePostJpaEntity> searchAll(MatePostJpaEntity matePostJpaEntity);

}
