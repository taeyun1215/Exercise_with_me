package user.application.port.out;

import user.domain.User;

import java.util.List;

public interface LoadUserPort {

    User findByUsername(String username);
    User findByNickname(String nickname);
    List<Long> findUserIdListByAddress(String address);
    String findAddressByuserId(Long userId);

}
