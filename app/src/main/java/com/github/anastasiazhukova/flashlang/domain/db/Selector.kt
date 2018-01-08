package com.github.anastasiazhukova.flashlang.domain.db

abstract class Selector protected constructor() {

    open class ByFieldSelector(fieldName: String, fieldValue: String) : Selector() {

        val mFieldName: String = fieldName
        val mFieldValue = fieldValue

    }
}