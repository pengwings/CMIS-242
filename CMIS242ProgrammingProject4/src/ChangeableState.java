public interface ChangeableState<T extends Enum<T>> {
    public abstract void changeState(T t);
}
