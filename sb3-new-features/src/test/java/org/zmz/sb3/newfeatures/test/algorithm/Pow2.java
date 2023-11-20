package org.zmz.sb3.newfeatures.test.algorithm;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pow2 {

    public static final Logger LOG = LoggerFactory.getLogger(Pow2.class);

    public static boolean isPowerOfTwo(final int value) {
        return (value & (value - 1)) == 0;
    }

    /**
     * Align a value to the next multiple up of alignment. If the value equals an alignment multiple then it
     * is returned unchanged.
     *
     * @param value     to be aligned up.
     * @param alignment to be used, must be a power of 2.
     * @return the value aligned to the next boundary.
     */
    public static long align(final long value, final int alignment) {
        if (!isPowerOfTwo(alignment)) {
            throw new IllegalArgumentException("alignment must be a power of 2:" + alignment);
        }
        return (value + (alignment - 1)) & -alignment;
    }

    @Test
    public void testAlign() {
        long l1 = Pow2.align(2, 4);
        assertEquals(4, l1);
        long l2 = Pow2.align(4, 4);
        assertEquals(4, l2);
    }

    @Test
    public void testSystemEnv() {
        Integer core = Integer.getInteger("micro.core.size", 168);
        LOG.info("配置核心: {}", core);
    }

    @Test
    public void testMethodHandlers() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(long.class, long.class, int.class);
        MethodHandle methodHandle = lookup.findStatic(Pow2.class, "align", methodType);
        Object o = methodHandle.invoke(4, 4);
        System.out.println(o);
    }
}
