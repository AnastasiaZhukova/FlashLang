package com.github.anastasiazhukova.lib.db.utils;

import android.provider.BaseColumns;
import android.support.annotation.Nullable;

import com.github.anastasiazhukova.lib.Constants;
import com.github.anastasiazhukova.lib.db.annotations.dbPrimaryKey;
import com.github.anastasiazhukova.lib.db.annotations.type.dbForeignKey;
import com.github.anastasiazhukova.lib.db.annotations.type.dbInteger;
import com.github.anastasiazhukova.lib.db.annotations.type.dbLong;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;
import com.github.anastasiazhukova.lib.utils.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import static com.github.anastasiazhukova.lib.db.utils.DbUtils.getFieldName;
import static com.github.anastasiazhukova.lib.db.utils.DbUtils.getTableName;

public final class SqlUtils {

    @Nullable
    public static String getCreateTableSql(final Class<?> pTable) throws Exception {

        if (pTable == null) {
            return null;
        }

        final String tableName = getTableName(pTable);
        if (StringUtils.isNullOrEmpty(tableName)) {
            return null;
        }

        final StringBuilder queryBuilder = new StringBuilder();
        final StringBuilder foreignKeyBuilder = new StringBuilder();

        boolean hasPrimaryKey = false;

        final Field[] fields = pTable.getDeclaredFields();

        for (final Field field : fields) {

            boolean isFieldQueryBuilt = false;
            boolean isPrimaryKey = false;
            boolean isPrimaryKeyNull = true;
            final boolean hasFieldPrimaryKey = hasPrimaryKey(field);

            final String fieldName = getFieldName(field);
            if (StringUtils.isNullOrEmpty(fieldName)) {
                continue;
            }

            final Annotation[] fieldAnnotations = field.getAnnotations();

            for (final Annotation annotation : fieldAnnotations) {

                if (isPrimaryKey(annotation)) {
                    if (isFieldQueryBuilt) {
                        queryBuilder.append(" ")
                                .append("PRIMARY KEY");
                        hasPrimaryKey = true;
                        if (!isPrimaryKeyNull(annotation)) {
                            queryBuilder.append(" ")
                                    .append("NOT NULL");
                        }
                        queryBuilder.append(",");
                    } else {
                        isPrimaryKey = true;
                        isPrimaryKeyNull = isPrimaryKeyNull(annotation);
                    }
                }

                {
                    if (isForeignKey(annotation)) {

                        final dbForeignKey foreignKey = (dbForeignKey) annotation;

                        if (foreignKeyBuilder.length() > 0) {
                            foreignKeyBuilder.append(",");
                        }

                        foreignKeyBuilder.append(String.format(Constants.Sql.FOREIGN_KEY_TEMPLATE,
                                fieldName,
                                foreignKey.referredTableName(),
                                foreignKey.referredTableColumnName()));

                        continue;
                    }
                }

                final String fieldType = getFieldType(annotation);

                if (fieldType != null && !isFieldQueryBuilt) {
                    queryBuilder.append(fieldName)
                            .append(" ")
                            .append(fieldType);
                    if (isPrimaryKey) {
                        queryBuilder.append(" ")
                                .append("PRIMARY KEY");
                        hasPrimaryKey = true;
                        if (!isPrimaryKeyNull) {
                            queryBuilder.append(" ")
                                    .append("NOT NULL");
                        }

                    }
                    if (!hasFieldPrimaryKey) {
                        queryBuilder.append(",");
                    }
                    isFieldQueryBuilt = true;
                }

            }

        }

        if (!hasPrimaryKey) {
            final Type[] genericInterfaces = pTable.getGenericInterfaces();
            for (final Type type :
                    genericInterfaces) {
                if (((Class) type).getCanonicalName().equals(BaseColumns.class.getCanonicalName())) {
                    queryBuilder.append(BaseColumns._ID)
                            .append(" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,");
                    hasPrimaryKey=true;
                }
            }
        }

        if(!hasPrimaryKey) {
            return null;
        }

        if (StringUtils.isNullOrEmpty(queryBuilder))

        {
            return null;
        }
        if (!StringUtils.isNullOrEmpty(foreignKeyBuilder))

        {
            queryBuilder.append(foreignKeyBuilder);
        }
        while (queryBuilder.charAt(queryBuilder.length() - 1) == ',')

        {
            queryBuilder.deleteCharAt(queryBuilder.length() - 1);
        }
        return String.format(Constants.Sql.TABLE_TEMPLATE, tableName, queryBuilder.toString());
    }

    private static boolean isPrimaryKeyNull(final Annotation pPrimaryKey) {
        if (isPrimaryKey(pPrimaryKey)) {
            final dbPrimaryKey primaryKey = (dbPrimaryKey) pPrimaryKey;
            return primaryKey.isNull();
        }

        return false;
    }

    private static boolean isPrimaryKey(final Annotation pAnnotation) {

        return pAnnotation != null && pAnnotation.annotationType().equals(dbPrimaryKey.class);
    }

    private static boolean hasPrimaryKey(final Field pField) {
        final Annotation[] declaredAnnotations = pField.getDeclaredAnnotations();
        for (final Annotation annotation :
                declaredAnnotations) {
            if (isPrimaryKey(annotation)) {
                return true;
            }

        }
        return false;
    }

    private static boolean isForeignKey(final Annotation pAnnotation) {

        return pAnnotation != null && pAnnotation.annotationType().equals(dbForeignKey.class);

    }

    private static String getFieldType(final Annotation pAnnotation) {

        if (pAnnotation == null) {
            return null;
        }

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
