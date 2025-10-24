package co.edu.uco.nose.crosscuting.helper;

public final class ObjectHelper {
	
    private ObjectHelper() {
    }

    public static <O> boolean isNull(O object) {
        return object == null;
    }


    public static <O> O getDefault(O object, O defaultValue) {
        return (isNull(object) ? defaultValue : object);
    }
}
