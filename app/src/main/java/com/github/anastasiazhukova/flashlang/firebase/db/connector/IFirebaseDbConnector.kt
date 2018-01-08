package com.github.anastasiazhukova.flashlang.firebase.db.connector

import com.github.anastasiazhukova.flashlang.db.IDbModel
import com.github.anastasiazhukova.flashlang.domain.db.Selector
import com.github.anastasiazhukova.flashlang.firebase.db.FirebaseQuery
import com.github.anastasiazhukova.flashlang.firebase.db.annotations.FirebaseDbElement
import com.google.firebase.database.Query

interface IFirebaseDbConnector {

    fun getKeyForElement(pTableName: String): String

    fun <Element : IDbModel<String>> insert(@FirebaseDbElement pElement: Element): Boolean

    fun <Element : IDbModel<String>> insert(@FirebaseDbElement pElements: Array<Element>): Boolean

    fun delete(pTableName: String, pSelector: Selector)

    fun <Element : IDbModel<String>> update(@FirebaseDbElement pElement: Element, pSelector: Selector)

    fun query(pFirebaseQuery: FirebaseQuery): Query?

    fun query(): FirebaseQuery

    fun <Element : IDbModel<String>> get(pFirebaseQuery: FirebaseQuery,
                                         pConverter: IDataSnapshotConverter<Element>,
                                         pCallback: IGetCallback<Element>)

    class Impl {
        companion object {
            fun getInstance(): IFirebaseDbConnector {
                return FirebaseDbConnector()
            }
        }
    }


}