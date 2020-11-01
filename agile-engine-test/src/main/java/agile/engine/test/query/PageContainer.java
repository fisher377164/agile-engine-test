package agile.engine.test.query;

import lombok.Value;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Value
public class PageContainer<T> {

    List<T> page;

    long totalCount;

    public static <DTO, E> PageContainer<DTO> from(final PageContainer<E> entities,
                                                   final Function<? super E, ? extends DTO> mapper) {
        List<DTO> page = entities.getPage().stream().map(mapper).collect(Collectors.toList());
        return new PageContainer<>(page, entities.getTotalCount());
    }
}
