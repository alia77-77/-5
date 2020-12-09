import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    private Properties properties;

    public Injector() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("./app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T inject(T object) {
        if (object == null) {
            throw new NullPointerException("Object is null");
        }
        injectObject(object);
        return object;
    }

    private <T> void injectObject(T object) {
        try {
            Class<?> clazz = object.getClass();
            for (Field field: clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    Object createdObject = createObject(field.getType());
                    if (createdObject != null) {
                        field.setAccessible(true);
                        field.set(object, createdObject);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object createObject(Class<?> type) {
        try {
            String className = properties.getProperty(type.getName());
            if (className != null) {
                Class<?> creatingClass = Class.forName(className);
                return creatingClass.newInstance();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
