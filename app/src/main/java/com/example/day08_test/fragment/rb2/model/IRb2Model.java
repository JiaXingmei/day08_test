package com.example.day08_test.fragment.rb2.model;

public interface IRb2Model {
    void getRb2ModelData(String url,Rb2ModelInterface rb2ModelInterface);

    public interface Rb2ModelInterface{
        void success(String data);
    }
}
