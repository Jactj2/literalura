package com.jonacruz.literalura.repository;

import com.jonacruz.literalura.model.ApiData;
import com.jonacruz.literalura.model.BookData;
import com.jonacruz.literalura.service.ConsumoAPI;
import com.jonacruz.literalura.service.DataConverter;
import com.jonacruz.literalura.service.IDataConverter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GutendexRepository {

    private final IDataConverter dataConverter = new DataConverter();

    private final ConsumoAPI consumoApi = new ConsumoAPI();

    public List<BookData> getQueryBook(String queryBook){
        String URLBOOKS = "https://gutendex.com/books/";
        var url = URLBOOKS + "?search=" + queryBook.replace(" ","+").toUpperCase() ;
        var stringJson = consumoApi.getRequestApi(url);
        ApiData dataApi = dataConverter.getModelData(stringJson, ApiData.class);
        return dataApi.results();
    }



}
