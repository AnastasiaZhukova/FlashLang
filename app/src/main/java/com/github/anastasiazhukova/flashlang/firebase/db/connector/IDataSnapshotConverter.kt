package com.github.anastasiazhukova.flashlang.firebase.db.connector

import com.google.firebase.database.DataSnapshot

interface IDataSnapshotConverter<out Element> {

    fun convert(pSnapshot: DataSnapshot): Element
}