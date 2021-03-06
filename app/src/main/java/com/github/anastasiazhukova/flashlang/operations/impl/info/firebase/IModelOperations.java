package com.github.anastasiazhukova.flashlang.operations.impl.info.firebase;

import com.github.anastasiazhukova.flashlang.db.IDbModel;
import com.github.anastasiazhukova.flashlang.domain.db.Selector;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.google.firebase.database.Query;

import java.util.List;

public interface IModelOperations<Model extends IDbModel<String>> {

    IOperation<Void> upload(Model pModel);

    IOperation<Void> uploadAll(Model[] pModels);

    IOperation<Void> loadList(ICallback<List<Model>> pCallback, Selector pSelector);

    IOperation<Void> loadSingle(ICallback<Model> pCallback, Selector pSelector);

    IOperation<Query> loadQuery(Selector pSelector);

    IOperation<Void> update(Model pModel, Selector pSelector);

    IOperation<Void> delete(Selector pSelector);

}
