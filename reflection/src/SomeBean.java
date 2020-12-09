import interfaces.SomeInterface;
import interfaces.SomeOtherInterface;

public class SomeBean {
    @AutoInjectable
    private SomeInterface someInterfaceImpl;
    @AutoInjectable
    private SomeOtherInterface someOtherInterfaceImpl;

    public void start() {
        someInterfaceImpl.doThings();
        someOtherInterfaceImpl.doStuff();
    }
}
