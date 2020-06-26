package net.clich;

public interface Modular<T> {

    void handle(T data, ExecutionContext context) throws Exception;

    default void error(T data, ExecutionContext context, Throwable th){

        throw new ModularHandleException(
            data != null ?
             data.getClass() + " is not handled with cause:" : "null is not handled with cause", 
             th);
    }

    static <T> void executeSupports(Modular<T> modular, T data, ExecutionContext context) {
        try{
            modular.handle(data, context);
        } catch(Throwable th) {
            modular.error(data, context, th);
        }
    }

    static class ModularHandleException extends RuntimeException {

        private static final long serialVersionUID = 4265203836330022115L;

        public ModularHandleException(Throwable th) {
            super(th);
        }

        public ModularHandleException(String message) {
            super(message);
        }

        public ModularHandleException(String message, Throwable th) {
            super(message, th);
        }
    }
    
}