package com.github.anastasiazhukova.flashlang.firebase.db.annotations

@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.VALUE_PARAMETER)
@kotlin.annotation.Retention
annotation class FirebaseDbElement(val targetTableName: String = "")
