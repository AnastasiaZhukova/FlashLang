package com.github.anastasiazhukova.lib.db.utils;

import android.support.annotation.Nullable;

import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.dbTableElement;
import com.github.anastasiazhukova.lib.db.annotations.type.dbInteger;
import com.github.anastasiazhukova.lib.db.annotations.type.dbLong;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class DbUtils {

    @Nullable
    public static String getTableName(@NotNull final Class<?> pClass) {

        String name = null;
        final dbTable dbTableAnnotation = pClass.getAnnotation(dbTable.class);
        if (dbTableAnnotation != null) {
            name = dbTableAnnotation.name();
        } else {
            final dbTableElement dbTableElementAnnotation = pClass.getAnnotation(dbTableElement.class);
            if (dbTableElementAnnotation != null) {
                name = dbTableElementAnnotation.targetTableName();
            }
        }
        return name;
    }

    @Nullable
    public static String getFieldName(@NotNull final Field pField) {
        final Annotation[] annotations = pField.getAnnotations();
        for (final Annotation annotation :
                annotations) {
            final Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationType.equals(dbInteger.class)) {
                return ((dbInteger) annotation).name();
            }
            if (annotationType.equals(dbLong.class)) {
                return ((dbLong) annotation).name();
            }
            if (annotationType.equals(dbString.class)) {
                return ((dbString) annotation).name();
            }
        }
        return null;
    }

}
