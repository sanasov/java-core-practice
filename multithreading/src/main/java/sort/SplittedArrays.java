package sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplittedArrays {

    private final int[] array;
    private final List<SortingInterval> intervals;

    public SplittedArrays(int[] array, List<SortingInterval> intervals) {
        this.array = array;
        this.intervals = intervals;
    }

    List<int[]> get() {
        return intervals.stream()
                .map(interval -> Arrays.copyOfRange(array, interval.startIndex(), interval.endIndex() + 1))
                .collect(Collectors.toList());
    }
}
