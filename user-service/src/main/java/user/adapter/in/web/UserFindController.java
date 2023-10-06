package user.adapter.in.web;

import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.adapter.in.request.RegisterUserRequest;
import user.application.port.in.command.FindUserIdListByAddressCommand;
import user.application.port.in.query.FindUserQuery;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserFindController {

    private final FindUserQuery query;

    @PostMapping("/address/{address}")
    public ResponseEntity<ReturnObject> findUserIdListByAddress(
            @PathVariable("address") String address
    ) {
        FindUserIdListByAddressCommand command = FindUserIdListByAddressCommand.builder()
                .address(address)
                .build();

        List<Long> userIds = query.findUserIdListByAddress(command);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(userIds)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
