package net.clich;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import lombok.Getter;
import lombok.Setter;
import net.clich.Modular.ModularHandleException;

public class ModularTest {

    @Getter
    @Setter
    public static class Holder<T> {
        T value;
    }

    @Test
    public void test_handle() {

        String data = "test";

        Holder<String> holder = new Holder<>();

        Modular.executeSupports(new Modular<String>() {
            @Override
            public void handle(String data, ExecutionContext context) throws Exception {
                holder.setValue(data);
            }
        }, data, ExecutionContext.defaults());

        assertEquals(data, holder.getValue(), "data must be delivered to holder");
    }

    @Test
    public void test_default_error() {
        assertThrows(ModularHandleException.class, () -> {
            Modular.executeSupports(new Modular<String>() {
                @Override
                public void handle(String data, ExecutionContext context) throws Exception {
                    data.getBytes();
                }
            }, null, ExecutionContext.defaults());
        });
    }
    
}