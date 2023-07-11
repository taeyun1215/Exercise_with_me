package mate.application.port.in.query;

import mate.adapter.in.request.SearchRequireMatePostRequest;
import mate.domain.MatePost;

import java.util.List;

public interface SearchMatePostQuery {

    List<MatePost> searchMatePostList(SearchRequireMatePostRequest searchRequireMatePostRequest);

}
