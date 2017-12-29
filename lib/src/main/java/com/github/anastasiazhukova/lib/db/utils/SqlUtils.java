package com.github.anastasiazhukova.lib.db.utils;

import android.support.annotation.Nullable;

import com.github.anastasiazhukova.lib.Constants;
import com.github.anastasiazhukova.lib.db.annotations.type.dbForeignKey;
import com.github.anastasiazhukova.lib.db.annotations.type.dbInteger;
import com.github.anastasiazhukova.lib.db.annotations.type.dbLong;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;
import com.github.anastasiazhukova.lib.utils.StringUtils;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.github.anastasiazhukova.lib.db.utils.DbUtils.getFieldName;
import static com.github.anastasiazhukova.lib.db.utils.DbUtils.getTableName;

public class SqlUtils {

    @Nullable
    public static String getCreateTableSql(@NotNull final Class<?> pTable) throws Exception  {
        final String tableName = getTableName(pTable);
        if (StringUtils.isNullOrEmpty(tableName)) {
            return null;
        }

        final StringBuilder queryBuilder = new StringBuilder();
        final StringBuilder foreignKeyBuilder = new StringBuilder();

        final Field[] fields = pTable.getDeclaredFields();

        for (final Field field : fields) {

            final String fieldName = getFieldName(field);
            if (StringUtils.isNullOrEmpty(fieldName)) {
                continue;
            }

            final Annotation[] fieldAnnotations = field.getAnnotations();

            for (final Annotation annotation : fieldAnnotations) {

                if (isForeignKey(annotation)) {

                    final dbForeignKey foreignKey = (dbForeignKey) annotation;

                    if (foreignKeyBuilder.length() > 0) {
                        foreignKeyBuilder.append(",");
                    }

                    foreignKeyBuilder.append(String.format(Constants.SqlConnector.FOREIGN_KEY_TEMPLATE,
                            fieldName,
                            foreignKey.referredTableName(),
                            foreignKey.referredTableColumnName()));

                    continue;
                }

                final String fieldType = getFieldType(annotation);

                if (fieldType != null) {
                    queryBuilder.append(fieldName)
                            .append(" ")
                            .append(fieldType)
                            .append(",");
                }
            }

        }

        if (StringUtils.isNullOrEmpty(queryBuilder)) {
            return null;
        }
        if (!StringUtils.isNullOrEmpty(foreignKeyBuilder)) {
            queryBuilder.append(foreignKeyBuilder);
        }
        while (queryBuilder.charAt(queryBuilder.length() - 1) == ',') {
            queryBuilder.deleteCharAt(queryBuilder.length() - 1);
        }
        return String.format(Constants.SqlConnector.TABLE_TAMPLATE, tableName, queryBuilder.toString());
    }

    private static boolean isForeignKey(@NotNull final Annotation pAnnotation) {
        return pAnnotation.annotationType().equals(dbForeignKey.class);
    }

    private static String getFieldType(@NotNull final Annotation pAnnotation) {
        final Class<? extends Annotation> annotationType = pAnnotation.annotationType();
        if (annotationType.equals(dbInteger.class)) {
            return ((dbInteger) pAnnotation).type();
        }
        if (annotationType.equals(dbLong.class)) {
            return ((dbLong) pAnnotation).type();
        }
        if (annotationType.equals(dbString.class)) {
            return ((dbString) pAnnotation).type();
        }
        if (annotationType.equals(dbForeignKey.class)) {
            return ((dbForeignKey) pAnnotation).type();
        }
        return null;
    }
}
