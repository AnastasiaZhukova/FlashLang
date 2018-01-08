package com.github.anastasiazhukova.flashlang.db.connector

import android.database.Cursor
import com.github.anastasiazhukova.flashlang.db.IDbModel
import com.github.anastasiazhukova.flashlang.domain.db.Selector

interface IDbTableConnector {

    fun <Element : IDbModel<String>> insert(pElement: Element): Boolean

    fun <Element : IDbModel<String>> insert(pElements: Array<Element>): Boolean

    fun <Element : IDbModel<String>> insert(pTableName: String, pElements: Array<Element>): Boolean

    fun delete(pTableName: String, vararg pSelectors: Selector): Boolean

    fun <Element : IDbModel<String>> update(pElement: Element, vararg pSelectors: Selector): Boolean

    fun <Element : IDbModel<String>> get(pTableName: String, pCursorConverter: ICursorConverter<Element>, vararg pSelectors: Selector): List<Element>?

    fun get(pTableName: String, vararg pSelectors: Selector): Cursor?

    interface ICursorConverter<out Element> {

        fun convert(pCursor: Cursor): Element

    }

}
