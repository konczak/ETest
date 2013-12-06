package pl.konczak.etest.service;

import java.util.List;

public interface IDTORowListPrepareService<T> {

    List<T> findAll();
}
