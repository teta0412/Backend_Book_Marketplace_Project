package com.teta.service;

import com.teta.model.Book;
import com.teta.model.Category;
import com.teta.model.Store;
import com.teta.request.CreateBookRequest;

import java.util.List;

public interface BookService {
    public Book createBook(CreateBookRequest req , Category category, Store store);

    void deleteBook(Long bookId) throws Exception;

    public List<Book> getStoreBook(Long storeId,
                                   boolean isAvailable,
                                   boolean isNovel,
                                   boolean isComic,
                                   String bookCategory);

    public List<Book> searchBook (String keyword);
    public Book findBookById(Long bookId) throws Exception;

    public Book updateAvailabilityStatus (Long bookId) throws Exception;
}
