package mate.application.port.in.usecase;

import mate.adapter.in.request.CreateMatePostRequest;

public interface CreateMatePostUseCase {

    void createMatePost(CreateMatePostRequest createMatePostRequest, Long userId);

}
