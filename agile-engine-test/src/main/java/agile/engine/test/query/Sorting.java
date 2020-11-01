package agile.engine.test.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Sort;

import java.util.Locale;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Sorting {

    String sortBy;

    SortOrder sortOrder;

    public static Sorting by(final String field, final String sortOrder) {
        if (field != null) {
            return new Sorting(field, SortOrder.fromString(sortOrder));
        }
        return null;
    }

    static Sort toSort(Sorting sorting) {
        if(sorting == null) {
            return Sort.unsorted();
        }
        return Sort.by(Sort.Direction.fromString(String.valueOf(sorting.sortOrder)), sorting.sortBy);
    }

    public enum SortOrder {
        ASC, DESC;

        static SortOrder fromString(final String value) {
            if (value == null) {
                return ASC;
            }
            return SortOrder.valueOf(value.toUpperCase(Locale.US));
        }
    }
}
