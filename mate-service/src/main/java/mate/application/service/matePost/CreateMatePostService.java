package mate.application.service.matePost;

import global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.adapter.in.request.CreateMatePostRequest;
import mate.application.port.in.usecase.CreateMatePostUseCase;
import mate.application.port.out.matePost.SaveMatePostPort;
import mate.domain.MatePost;
import mate.application.kafka.KafkaProducer;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class CreateMatePostService implements CreateMatePostUseCase {

    private final SaveMatePostPort saveMatePostPort;
    private final KafkaProducer kafkaProducer;

    @Override
    public void createMatePost(CreateMatePostRequest createMatePostRequest, Long userId) {
        MatePost matepost = createMatePostRequest.toEntity(userId);
        saveMatePostPort.saveMatePost(matepost);
        kafkaProducer.send("user-topic", createMatePostRequest);
    }

}
