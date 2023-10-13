package user.adapter.in.web;

import global.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.adapter.in.request.RegisterUserRequest;
import user.application.port.in.command.FindAddressByUserIdCommand;
import user.application.port.in.command.FindUserIdListByAddressCommand;
import user.application.port.in.query.FindUserQuery;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserFindController {

    private final FindUserQuery query;

    @GetMapping("/address/{address}")
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

    @GetMapping("/userId/{userId}")
    public ResponseEntity<ReturnObject> findAddressByuserId(
            @PathVariable("userId") Long userId
    ) {
        FindAddressByUserIdCommand command = FindAddressByUserIdCommand.builder()
                .userId(userId)
                .build();

        String address = query.findAddressByUserId(command);

        ReturnObject returnObject = ReturnObject.builder()
                .success(true)
                .data(address)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(returnObject);
    }
}
