package com.example.day08_test.fragment.rb3.model;

import java.util.Map;

public interface IRb3Model {
    void getModelData(String url, Map map, Rb3ModelInterface rb3ModelInterface);

    public interface Rb3ModelInterface{
        void success(Object data);
        void failed(Exception e);
    }
}
