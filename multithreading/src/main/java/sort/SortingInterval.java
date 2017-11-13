package sort;

public class SortingInterval {
    private int threadNumber;
    private int threadsCount;
    private int arrLength;


    public SortingInterval(int threadNumber, int threadsCount, int arrLength) {
        this.threadNumber = threadNumber;
        this.threadsCount = threadsCount;
        this.arrLength = arrLength;
    }

    public int startIndex() {
        return (threadNumber * arrLength) / threadsCount;
    }

    public int endIndex() {
        return startIndex() + distance();
    }

    private int distance() {
        return arrLength / threadsCount - 1;
    }
}
