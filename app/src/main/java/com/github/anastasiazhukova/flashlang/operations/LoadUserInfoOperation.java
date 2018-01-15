package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.firebase.auth.FirebaseUserManager;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class LoadUserInfoOperation implements IOperation<User> {

    @Override
    public User perform() {
        final FirebaseUser currentUser = FirebaseUserManager.Impl.Companion.getInstance()
                .getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("No current user");
        }

        final String uid = currentUser.getUid();
        final IDbTableConnector tableConnector = IDbTableConnector.Companion.getInstance();

        final List<User> users = tableConnector.get(User.DbKeys.TABLE_NAME, new User.CursorConverter(), null, new User.ByIdSelector(uid));
        assert users != null;
        if (users.size() <= 0) {
            final User user = createNewUser(currentUser);
            uploadUserToDb(user);
            return user;
        } else {
            return users.get(0);
        }

    }

    private void uploadUserToDb(final User pUser) {
        ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.THREAD)
                .execute(new UploadUserToDbOperation(pUser));
    }

    private User createNewUser(final FirebaseUser pUser) {
        final User user = new User(pUser);
        return user;
    }
}
