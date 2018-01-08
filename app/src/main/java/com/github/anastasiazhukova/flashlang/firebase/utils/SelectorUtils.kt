package com.github.anastasiazhukova.flashlang.firebase.utils

import com.github.anastasiazhukova.flashlang.domain.db.Selector
import com.google.firebase.database.Query

class SelectorUtils {

    companion object {

        fun applySelector(pQuery: Query, pSelector: Selector): Query {
            val finalQuery: Query
            when (pSelector) {
                is Selector.ByFieldSelector -> {
                    finalQuery = pQuery.orderByChild(pSelector.mFieldName)
                            .equalTo(pSelector.mFieldValue)
                    return finalQuery
                }
                else -> {
                    throw IllegalStateException("No such selector")
                }
            }
        }

        private fun Query.applySelector(pSelector: Selector.ByFieldSelector) {
            this.orderByChild(pSelector.mFieldName)
                    .orderByChild(pSelector.mFieldValue)
        }

    }

}