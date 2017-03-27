package io.cdep.cdep.utils;

import io.cdep.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static io.cdep.cdep.utils.Invariant.notNull;

public class ReflectionUtils {

  /**
   * Invoke but convert atypical exceptions to RuntimeException. If the invoked method threw a RuntimeException then unwrap and throw.
   */
  public static Object invoke(@org.jetbrains.annotations.NotNull @NotNull Method method, Object thiz, Object... args) {
    notNull(method);
    try {
      return method.invoke(thiz, args);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      // Unwrap RuntimeException
      if (e.getTargetException() instanceof RuntimeException) {
        throw (RuntimeException) e.getTargetException();
      }
      throw new RuntimeException(e);
    }
  }

  /**
   * Get method but convert atypical exceptions into RuntimeException. Should be used
   * when it is a bug if the method doesn't exist.
   */
  public static Method getMethod(@org.jetbrains.annotations.NotNull @NotNull Class<?> clazz, @org.jetbrains.annotations.NotNull @NotNull String name,
      Class<?>... parameterTypes) {
    notNull(clazz);
    notNull(name);
    try {
      return clazz.getMethod(name, parameterTypes);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Get field value but convert atypical exceptions into RuntimeException. Should be used
   * when it is a bug if the method doesn't exist.
   */
  public static Object getFieldValue(@org.jetbrains.annotations.NotNull @NotNull Field field, Object instance) {
    notNull(field);
    notNull(instance);
    try {
      return field.get(instance);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
