package com.github.anastasiazhukova.flashlang.db;

import com.github.anastasiazhukova.flashlang.Constants;
import com.github.anastasiazhukova.flashlang.db.tables.CollectionCardLink;
import com.github.anastasiazhukova.flashlang.db.tables.UserCollectionLink;
import com.github.anastasiazhukova.flashlang.models.Card;
import com.github.anastasiazhukova.flashlang.models.Collection;
import com.github.anastasiazhukova.flashlang.models.User;
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
                Collection.class,
                Card.class,
                CollectionCardLink.class,
                UserCollectionLink.class
        };
    }
}
