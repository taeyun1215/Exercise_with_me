package mate.adapter.out.persistence.matePost.response;

import lombok.Builder;
import lombok.Getter;
import mate.domain.MatePost;

@Getter
@Builder
public class ViewMatePostResponse {

    private Long id;
    private String title;
    private String content;
    private String gym;
    private String writer;
    private int view;

    public static ViewMatePostResponse from(MatePost matePost) {
        return ViewMatePostResponse.builder()
                .id(matePost.getMatePostId())
                .title(matePost.getTitle())
                .content(matePost.getContent())
                .gym(matePost.getGym())
                .writer(matePost.getWriter())
                .view(matePost.getView())
                .build();
    }
}
