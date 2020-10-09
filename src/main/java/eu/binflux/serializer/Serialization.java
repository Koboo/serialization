package eu.binflux.serializer;

public interface Serialization {

    <T> byte[] serialize(T object);

    <T> T deserialize(byte[] bytes);

}
