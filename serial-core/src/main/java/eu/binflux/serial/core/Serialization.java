package eu.binflux.serial.core;

public interface Serialization {

    <T> byte[] serialize(T object);

    <T> T deserialize(byte[] bytes);
}
