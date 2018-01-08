package com.github.anastasiazhukova.flashlang.firebase.db.connector

import com.github.anastasiazhukova.flashlang.db.IDbModel
import com.github.anastasiazhukova.lib.contracts.ICallback

interface IGetCallback<Element : IDbModel<String>> : ICallback<List<Element>>