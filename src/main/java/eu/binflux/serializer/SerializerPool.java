package eu.binflux.serializer;

import org.apache.commons.pool2.impl.GenericObjectPool;


public class SerializerPool extends GenericObjectPool {

    final Class<? extends Serialization> classOfSerial;

    public SerializerPool(Class<? extends Serialization> classOfSerial) {
        super(new SerializerFactory(classOfSerial));
        this.classOfSerial = classOfSerial;
    }

    private Serialization obtain() throws Exception {
        return classOfSerial.cast(borrowObject());
    }

    private void free(Serialization serialization) {
        returnObject(serialization);
    }

    public <T> byte[] serialize(T object) {
        try {
            Serialization serialization = obtain();
            byte[] serialized = serialization.serialize(object);
            free(serialization);
            return serialized;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T deserialize(byte[] serialized) {
        try {
            Serialization serialization = obtain();
            T deserialized = serialization.deserialize(serialized);
            free(serialization);
            return deserialized;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

}
