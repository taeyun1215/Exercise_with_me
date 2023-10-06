package user.application.port.in.query;

import user.application.port.in.command.FindUserIdListByAddressCommand;

import java.util.List;

public interface FindUserQuery {

    List<Long> findUserIdListByAddress(FindUserIdListByAddressCommand command);

}
