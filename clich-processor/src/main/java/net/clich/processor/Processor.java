package net.clich.processor;

public interface Processor<T> {
    
    static class ProcessorException extends RuntimeException {

        private static final long serialVersionUID = 5224434814603127888L;

        public ProcessorException(Throwable th) {
            super(th);
        }

    }

    void handle(T data) throws Exception;

    default void error(final T data, final Throwable th) {

        throw new ProcessorException(th);

    }

}