package com.github.anastasiazhukova.flashlang.db.connector;

import android.database.Cursor;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.lib.Constants;

import java.util.List;

public interface IDbTableConnector<Element extends IDbModel> {

    List<Element> get(String pTableName, ISelector pSelector, ICursorConverter<Element> pCursorConverter);

    boolean insert(Element pElement);

    boolean insert(Element[] pElements);

    boolean insert(String pTableName, Element[] pElements);

    boolean delete(String pTableName, ISelector pSelector);

    boolean update(Element pElement, ISelector pSelector);

    interface ISelector {

        String getSelection();

        class ByFieldSelector implements ISelector {

            String mFieldName;
            String mFiledValue;

            public ByFieldSelector(final String pFieldName, final String pFiledValue) {
                mFieldName = pFieldName;
                mFiledValue = pFiledValue;
            }

            @Override
            public String getSelection() {
                return String.format(Constants.Sql.WHERE_EQUAL_TEMPLATE, mFieldName, mFiledValue);
            }
        }

    }

    interface ICursorConverter<Element> {

        Element convert(Cursor pCursor);

    }

}
