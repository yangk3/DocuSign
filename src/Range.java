public class Range {

    private int low;
    private int high;

    public Range(int low, int high) {
        this.low = low;
        this.high = high;
    }

    //inclusive upper bound and noninclusive lower bound
    public boolean contains(int number) {
        return (number > low && number <= high);
    }

    public int getLow() {
        return this.low;
    }

    public int getHigh() {
        return this.high;
    }
}
