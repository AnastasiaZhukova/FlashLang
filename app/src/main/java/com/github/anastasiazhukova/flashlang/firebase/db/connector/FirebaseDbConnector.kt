package com.github.anastasiazhukova.flashlang.firebase.db.connector

import com.github.anastasiazhukova.flashlang.db.IDbModel
import com.github.anastasiazhukova.flashlang.domain.db.Selector
import com.github.anastasiazhukova.flashlang.firebase.db.FirebaseQuery
import com.github.anastasiazhukova.flashlang.firebase.db.annotations.FirebaseDbElement
import com.github.anastasiazhukova.flashlang.firebase.utils.DbUtils
import com.github.anastasiazhukova.flashlang.firebase.utils.SelectorUtils
import com.github.anastasiazhukova.lib.logs.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.*

class FirebaseDbConnector : IFirebaseDbConnector {

    private val mFirebaseDatabase = FirebaseDatabase.getInstance()
    val LOG_TAG: String = "FirebaseConnector"

    override fun getKeyForElement(pTableName: String): String {
        val key = mFirebaseDatabase.reference.child(pTableName).push().key
        return key
    }

    override fun <Element : IDbModel<String>> insert(pElement: Element): Boolean {
        val tableName = getTableName(pElement)
        var isSuccessful = false
        if (!tableName.isNullOrBlank()) {
            val task = mFirebaseDatabase.reference.child(tableName).child(pElement.id)
                    .setValue(pElement.convertToInsert())
                    .addOnCompleteListener({ task ->
                        isSuccessful = task.isSuccessful
                    })
            try {
                Tasks.await(task)
            } catch (e: Exception) {
            }
        }
        return isSuccessful
    }

    override fun <Element : IDbModel<String>> insert(pElements: Array<Element>): Boolean {
        var isSuccessful = true
        for (element in pElements) {
            isSuccessful = (isSuccessful and (insert(element)))
        }
        return isSuccessful
    }

    override fun delete(pTableName: String, pSelector: Selector) {
        val query = query(pTableName, pSelector)
        if (query != null) {
            try {
                val listener: ValueEventListener = object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (appleSnapshot in dataSnapshot.children) {
                            appleSnapshot.ref.removeValue()
                            query.removeEventListener(this)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        query.removeEventListener(this)
                    }
                }
                query.addValueEventListener(listener)
            } catch (e: Exception) {

            }
        }
    }

    override fun <Element : IDbModel<String>> update(pElement: Element, pSelector: Selector) {
        val tableName = getTableName(pElement)
        if (!tableName.isNullOrBlank()) {
            val query = query(tableName!!, pSelector)
            if (query != null) {
                val listener: ValueEventListener = object : ValueEventListener {

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (appleSnapshot in dataSnapshot.children) {
                            appleSnapshot.ref.updateChildren(pElement.convertToUpdate())
                            query.removeEventListener(this)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        query.removeEventListener(this)
                    }
                }
                query.addValueEventListener(listener)
            }

        }
    }

    override fun query(pFirebaseQuery: FirebaseQuery): Query? {
        val tableName = pFirebaseQuery.tableName ?: return null
        val selector = pFirebaseQuery.selector
        val limit = pFirebaseQuery.limit
        val startAt = pFirebaseQuery.startAt

        val query = query(tableName, selector)
        if (query != null) {
            if (startAt != null) {
                query.startAt(startAt)
            }
        }
        return query
    }

    override fun query(): FirebaseQuery {
        return FirebaseQuery()
    }

    override fun <Element : IDbModel<String>> get(pFirebaseQuery: FirebaseQuery,
                                                  pConverter: IDataSnapshotConverter<Element>,
                                                  pCallback: IGetCallback<Element>) {
        val query = query(pFirebaseQuery)
        if (query != null) {
            val limit = pFirebaseQuery.limit
            val elements: MutableList<Element> = mutableListOf()
            val listener: ValueEventListener = object : ValueEventListener {
                override fun onCancelled(pError: DatabaseError?) {
                    query.removeEventListener(this)
                    pCallback.onError(InterruptedException("Event Canceled"))
                }

                override fun onDataChange(pSnapshot: DataSnapshot?) {
                    Log.d(LOG_TAG, "onDataChange: " + pSnapshot)
                    query.removeEventListener(this)

                    if (pSnapshot != null) {
                        val children = pSnapshot.children
                        if (limit == 0) {
                            children.mapTo(elements) {
                                pConverter.convert(it)
                            }
                        } else {
                            var count = 0
                            for (child in children) {
                                if (count == limit) break
                                elements.add(pConverter.convert(child))
                                count++
                            }
                        }

                    }
                    pCallback.onSuccess(elements)
                }
            }
            query.addListenerForSingleValueEvent(listener)
        } else {
            pCallback.onError(NullPointerException("FirebaseQuery is null!"))
        }
    }

    private fun query(pTableName: String, pSelector: Selector?): Query? {
        val ref = mFirebaseDatabase.reference.child(pTableName)
        if (pSelector == null) {
            return ref
        } else {
            var query: Query? = SelectorUtils.applySelector(ref, pSelector)
            return query
        }
    }

    private fun <Element : IDbModel<String>> getTableName(@FirebaseDbElement pElement: Element): String? {
        return DbUtils.getTableName(pElement.javaClass)
    }


}