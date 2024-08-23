package com.teta.repository;

import com.teta.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByStoreId(Long storeId);

    @Query(value = "SELECT f from Book f WHERE f.name LIKE %:keyword% OR f.bookCategory.name LIKE %:keyword%", nativeQuery = true)
    List<Book> searchBook(@Param("keyword") String keyword);

}
