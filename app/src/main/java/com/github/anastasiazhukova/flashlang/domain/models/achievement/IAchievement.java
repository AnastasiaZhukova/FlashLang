package com.github.anastasiazhukova.flashlang.domain.models.achievement;

import com.github.anastasiazhukova.flashlang.domain.models.IIdentifiable;

public interface IAchievement extends IIdentifiable<String> {

    String getOwnerId();

    long getTotalConnections();

    long getTotalWords();

}
