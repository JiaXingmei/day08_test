package com.example.day08_test.fragment.rb1.model;

public interface IRb1Model {
    void getRb1ModelData(String url,Class clazz, Rb1ModelInterface rb1ModelInterface);
    public interface Rb1ModelInterface{
        void success(Object data);
        void failed();
    }
}
