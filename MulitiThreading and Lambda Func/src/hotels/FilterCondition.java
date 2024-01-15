package hotels;

// Functional interface can only have one abstract method.
// Lambda functions only works with functional interfaces.
@FunctionalInterface
public interface FilterCondition {
    abstract boolean test(Hotel hotel);
}