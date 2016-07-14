package net.chandol.study._testhelper;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AssertionInfo;
import org.assertj.core.error.ShouldBeEqual;
import org.assertj.core.internal.Failures;
import org.assertj.core.presentation.StandardRepresentation;
import org.assertj.core.util.VisibleForTesting;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class ExtendedObjectAssert<A> extends AbstractObjectAssert<ExtendedObjectAssert<A>, A> {

    @VisibleForTesting
    Failures failures = Failures.instance();

    protected ExtendedObjectAssert(A target) {
        super(target, ExtendedObjectAssert.class);
    }

    public ExtendedObjectAssert<A> is(String filedPath, Object expected) {
        assertEquals(info, getValue(filedPath, actual), expected);
        return this;
    }

    private boolean checkEqual(Object actual, Object expected) {
        if (actual == null) return expected == null;
        if (expected == null) return false;
        return actual.equals(expected);
    }

    private void assertEquals(AssertionInfo info, Object actual, Object expected) {
        if (!checkEqual(actual, expected))
            throw failures.failure(info, ShouldBeEqual.shouldBeEqual(actual, expected, new StandardRepresentation()));
    }


    private Object getValue(String path, Object target) {
        // 하위 객체를 찾아야 할 경우
        if (path.contains(".")) {
            String[] paths = path.split("\\.");
            Object newTarget = findInstance(target, paths[0]);
            StringJoiner joiner = new StringJoiner(".");
            for (int i = 1; i < paths.length; i++) joiner.add(paths[i]);

            return getValue(joiner.toString(), newTarget);
        } else {
            return findInstance(target, path);
        }
    }

    private Object findInstance(Object target, String name) {

        try {
            Field field = target.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(target);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static final <T> ExtendedObjectAssert<T> objectAssertThat(T object) {
        return new ExtendedObjectAssert<>(object);
    }
}