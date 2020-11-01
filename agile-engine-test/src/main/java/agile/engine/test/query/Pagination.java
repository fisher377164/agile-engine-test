package agile.engine.test.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pagination {

    int pageIndex;

    int pageSize;

    public static Pagination with(Integer pageIndex, Integer pageSize) {
        if (pageSize != null && pageIndex != null) {
            return new Pagination(pageIndex, pageSize);
        }
        return null;
    }

    public static Pageable toPageable(Pagination pagination, Sorting sorting) {
        if(pagination == null) {
            return Pageable.unpaged();
        }

        return PageRequest.of(pagination.getPageIndex(), pagination.getPageSize(), Sorting.toSort(sorting));
    }
}
