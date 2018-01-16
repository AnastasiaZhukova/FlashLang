package com.github.anastasiazhukova.flashlang.db;

import com.github.anastasiazhukova.flashlang.Constants;
import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.lib.db.IDb;

public final class AppDb implements IDb {

    @Override
    public String getName() {
        return Constants.Db.DB_NAME;
    }

    @Override
    public int getVersion() {
        return Constants.Db.DB_VERSION;
    }

    @Override
    public Class<?>[] getTableModels() {
        return new Class[]{
                User.class,
                Achievement.class,
                Collection.class,
                Card.class,
        };
    }
}
