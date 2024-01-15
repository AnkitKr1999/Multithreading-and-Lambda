package hotels;

public interface FilterCondition {
    abstract boolean test(Hotel hotel);
}