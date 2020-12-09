package interfaces.impl;

import interfaces.SomeInterface;

public class SomeInterfaceSecondImpl implements SomeInterface {
    @Override
    public void doThings() {
        System.out.println("SomeInterfaceSecondImpl");
    }
}
