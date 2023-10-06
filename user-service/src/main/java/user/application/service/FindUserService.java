package user.application.service;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import user.application.port.in.command.FindUserIdListByAddressCommand;
import user.application.port.in.query.FindUserQuery;
import user.application.port.out.LoadUserPort;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class FindUserService implements FindUserQuery {

    private final LoadUserPort loadUserPort;

    @Override
    public List<Long> findUserIdListByAddress(FindUserIdListByAddressCommand command) {
        return loadUserPort.findUserIdListByAddress(command.getAddress());
    }

}
