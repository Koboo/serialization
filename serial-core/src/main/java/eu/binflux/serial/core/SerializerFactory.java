package eu.binflux.serial.core;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class SerializerFactory extends BasePooledObjectFactory {

    final Class<? extends Serialization> classOfSerial;

    public SerializerFactory(Class<? extends Serialization> classOfSerial) {
        this.classOfSerial = classOfSerial;
    }

    @Override
    public Object create() throws Exception {
        return classOfSerial.newInstance();
    }

    @Override
    public PooledObject wrap(Object obj) {
        return new DefaultPooledObject(obj);
    }
}
