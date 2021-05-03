![Binflux-Serialization](binflux-serialization.png)

Serialization implements different Java serialization 
libraries into a pool to serialize or deserialize objects in byte arrays easily & quickly.

## Overview

* [Usage](#usage)
* [Implemented Serialization](#implemented-serialization)
* [Implement new Serialization](#implement-serialization)
* [Add as dependency](#add-as-dependency)
* [Build from source](#build-from-source)

## Usage

Creating a new `SerializerPool` instance using standard `JavaSerialization.class`:

```java
SerializerPool pool = new SerializerPool(JavaSerialzation.class);

String string = new String("Lorem ipsum dolor sit amet.");
    
byte[] serialized = pool.serialize(string);
    
String deserialized = pool.deserialize(serialized);
```

If something goes wrong during de- or serialization, the exception is thrown and instead of the object you get `null`.

## Implemented Serialization

| Serialization (Class)      | Serialization (Framework) |
|----------------------------|---------------------------|
| `KryoSerialization`        | Kryo                      |
| `KryoUnsafeSerialization`  | Kryo                      |
| `JavaSerialization`        | Java I/O                  |
| `FSTSerialization`         | fast-serialization        |
| `FSTNoSharedSerialization` | fast-serialization        |
| `HessianSerialization`     | Hessian                   |
| `Hessian2Serialization`    | Hessian                   |
| `QuickserSerialization`    | QuickSer                  |
| `ElsaSerialization`        | Elsa                      |
| `ElsaStreamSerialization`  | Elsa                      |

Frameworks:
* [Kryo](https://github.com/EsotericSoftware/kryo)
* [Java Tutorial](https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html#:~:text=To%20serialize%20an%20object%20means,interface%20or%20its%20subinterface%2C%20java.)
* [fast-serialization](https://github.com/RuedigerMoeller/fast-serialization)
* [Hessian](http://hessian.caucho.com/)
* [QuickSer](https://github.com/romix/quickser)
* [Elsa](http://www.mapdb.org/)

## Implement Serialization
 
Implementing a new `Serialization`:
 
 ```java
 public class CustomSerialization implements Serialization {
     
     @Override
     public <T> byte[] serialize(T object) {
         // Serialization workflow here
     }
 
     @Override
     public <T> T deserialize(byte[] bytes) {
         // Deserialization workflow here
     }
 } 
 ```

## Add as dependency

Add `repo.koboo.eu` as repository. 

```java
repositories {
    maven { url 'https://repo.koboo.eu/releases' }
}
```

And add specific serializer as dependency. (e.g. `1.0` is the release-version)
```java
dependencies {
    // !Always needed!
    compile 'eu.koboo:serial-core:1.0'
    // Select specific serializer
    compile 'eu.koboo:serial-elsa:1.0'
    compile 'eu.koboo:serial-fst:1.0'
    compile 'eu.koboo:serial-hessian:1.0'
    compile 'eu.koboo:serial-kryo:1.0'
    compile 'eu.koboo:serial-quickser:1.0'
}
```

## Build from source

* Clone repository
* Run `./gradlew buildApp`
* Output `/build/libs/serialization-{version}-all.jar` 
* Build task [build.gradle](https://github.com/Koboo/serialization/blob/master/build.gradle)

