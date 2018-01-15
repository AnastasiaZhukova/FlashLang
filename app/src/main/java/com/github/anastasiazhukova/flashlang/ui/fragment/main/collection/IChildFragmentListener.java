package com.github.anastasiazhukova.flashlang.ui.fragment.main.collection;

public interface IChildFragmentListener<T> {

    void onItemClick(T pElement);

    void onBackClick();

}
