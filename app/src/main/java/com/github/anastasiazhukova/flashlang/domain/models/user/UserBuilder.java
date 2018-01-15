package com.github.anastasiazhukova.flashlang.domain.models.user;

public class UserBuilder {

    private String mId;
    private String mName;
    private String mPictureUrl;
    private int mWordsCount;
    private int mLanguagesCount;

    public UserBuilder setId(final String pId) {
        mId = pId;
        return this;
    }

    public UserBuilder setName(final String pName) {
        mName = pName;
        return this;
    }

    public UserBuilder setPictureUrl(final String pPictureUrl) {
        mPictureUrl = pPictureUrl;
        return this;
    }

    public UserBuilder setWordsCount(final int pWordsCount) {
        mWordsCount = pWordsCount;
        return this;
    }

    public UserBuilder setConnectionsCount(final int pLanguagesCount) {
        mLanguagesCount = pLanguagesCount;
        return this;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }

    public int getWordsCount() {
        return mWordsCount;
    }

    public int getLanguagesCount() {
        return mLanguagesCount;
    }

    public User createUser() {
        return new User(this);
    }
}