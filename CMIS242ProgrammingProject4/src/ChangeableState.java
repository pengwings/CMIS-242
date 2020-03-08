/**
 * ChangeableState.java
 * Brian Yu
 * 3/8/2020
 * This generic interface declares enumerated types as a bounded type parameter and contains an abstract method
 * to change state.
 */
public interface ChangeableState<T extends Enum<T>> {
    void changeState(T t);
}
