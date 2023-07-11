package mate.application.port.in.usecase;

import mate.adapter.in.request.ModifyMatePostRequest;

public interface ModifyMatePostUseCase {

    void modifyMatePost(ModifyMatePostRequest modifyMatePostRequest, Long matePostId, Long userId);

}