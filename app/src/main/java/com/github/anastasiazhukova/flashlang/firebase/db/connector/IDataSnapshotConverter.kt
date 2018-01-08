package com.github.anastasiazhukova.flashlang.firebase.db.connector

import com.github.anastasiazhukova.flashlang.db.IDbModel
import com.google.firebase.database.DataSnapshot

interface IDataSnapshotConverter<Element : IDbModel<String>> {

    fun convert(pSnapshot: DataSnapshot): Element
}