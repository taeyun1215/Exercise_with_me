package user.application.port.in.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindAddressByUserIdCommand {

    private final Long userId;

}
