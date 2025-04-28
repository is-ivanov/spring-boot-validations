package by.iivanov.springbootvalidation;

public interface ServiceAsInterfaceWithout {
    void parameters(String name, Integer myId);

    void objectValid(MyRequest request);

    void objectValidated(MyRequest request);
}
