package com.teta.service;

import com.teta.model.Book;
import com.teta.model.Category;
import com.teta.model.Store;
import com.teta.repository.BookRepository;
import com.teta.request.CreateBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(CreateBookRequest req, Category category, Store store) {
        Book book = new Book();
        book.setBookCategory(category);
        book.setStore(store);
        book.setDescription(req.getDescription());
        book.setImages(req.getImages());
        book.setName(req.getName());
        book.setAuthor(req.getAuthor());
        book.setPrice(req.getPrice());
        book.setAvailable(req.isAvailable());
        book.setComic(req.isComic());
        book.setNovel(req.isNovel());

        Book savedBook = bookRepository.save(book);
        store.getBooks().add(savedBook);

        return savedBook;
    }

    @Override
    public void deleteBook(Long bookId) throws Exception {
        Book book = findBookById(bookId);
        book.setStore(null);
        bookRepository.save(book);
    }

    @Override
    public List<Book> getStoreBook(Long storeId,
                                   boolean isAvailable,
                                   boolean isNovel,
                                   boolean isComic,
                                   String bookCategory) {
        List<Book> books = bookRepository.findByStoreId(storeId);

        if (isAvailable){
            books = filterByAvailable(books,isAvailable);
        }
        if (isComic){
            books = filterByComic(books,isComic);
        }
        if (isNovel){
            books = filterByNovel(books,isNovel);
        }
        if (bookCategory != null && !bookCategory.equals("")){
            books = filterByCategory(books,bookCategory);
        }

        return List.of();
    }

    private List<Book> filterByCategory(List<Book> books, String bookCategory) {
        return books.stream().filter(book -> {
            if (book.getBookCategory()!=null){
                return book.getBookCategory().getName().equals(bookCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Book> filterByNovel(List<Book> books, boolean isNovel) {
        return books.stream().filter(book -> book.isNovel()==isNovel).collect(Collectors.toList());
    }

    private List<Book> filterByComic(List<Book> books, boolean isComic) {
        return books.stream().filter(book -> book.isNovel()==isComic).collect(Collectors.toList());
    }

    private List<Book> filterByAvailable (List<Book> books, boolean isAvailable){
        return books.stream().filter(book -> book.isAvailable()==isAvailable).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBook(String keyword) {
        return bookRepository.searchBook(keyword);
    }

    @Override
    public Book findBookById(Long bookId) throws Exception {
        Optional<Book> optionalBook =bookRepository.findById(bookId);

        if (optionalBook.isEmpty()){
            throw new Exception("Book not exist...");
        }
        return optionalBook.get();
    }

    @Override
    public Book updateAvailabilityStatus(Long bookId) throws Exception {
        Book book = findBookById(bookId);
        book.setAvailable(!book.isAvailable());
        return bookRepository.save(book);
    }
}
