package com.jonacruz.literalura.service;

public interface IDataConverter {

    <T> T getModelData(String json, Class<T> clase);

}
