package com.github.anastasiazhukova.flashlang.domain.models.collection;

import com.github.anastasiazhukova.flashlang.domain.models.IIdentifiable;

public interface ICollection extends IIdentifiable<String> {

    String getName();

    String getCoverUrl();

}
