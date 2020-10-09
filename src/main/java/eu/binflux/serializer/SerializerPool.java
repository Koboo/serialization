package eu.binflux.serializer;

import org.apache.commons.pool2.impl.GenericObjectPool;

public class SerializerPool extends GenericObjectPool {

    final Class<? extends Serialization> classOfSerial;

    public SerializerPool(Class<? extends Serialization> classOfSerial) {
        super(new SerializerFactory(classOfSerial));
        this.classOfSerial = classOfSerial;
    }

    public Serialization obtain() {
        try {
            return classOfSerial.cast(borrowObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void free(Serialization serialization) {
        returnObject(serialization);
    }



}
