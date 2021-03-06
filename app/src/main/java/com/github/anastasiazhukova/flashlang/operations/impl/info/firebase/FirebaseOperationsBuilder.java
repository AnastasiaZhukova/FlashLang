package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase;

import com.github.anastasiazhukova.flashlang.domain.models.achievement.Achievement;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.domain.models.collection.Collection;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.operations.AchievementModelOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.operations.CardModelOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.operations.CollectionModelOperations;
import com.github.anastasiazhukova.flashlang.operations.impl.info.firebase.operations.UserModelOperations;

public class FirebaseOperationsBuilder {

    public IModelOperations<User> user() {
        return new UserModelOperations();
    }

    public IModelOperations<Card> card() {
        return new CardModelOperations();
    }

    public IModelOperations<Collection> collection() {
        return new CollectionModelOperations();
    }

    public IModelOperations<Achievement> achievement() {
        return new AchievementModelOperations();
    }

}
