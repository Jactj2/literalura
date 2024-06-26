package com.jonacruz.literalura.service;

import com.jonacruz.literalura.model.BookData;
import com.jonacruz.literalura.model.Books;
import com.jonacruz.literalura.model.PersonModel;
import com.jonacruz.literalura.model.PersonsData;
import com.jonacruz.literalura.repository.AuthorsRepository;
import com.jonacruz.literalura.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsService {

    @Autowired
    AuthorsRepository authorsRepository;
    @Autowired
    BooksRepository booksRepository;

    List<PersonModel> dbAuthors;
    List<Books> dbBooks;

    public AuthorsService(AuthorsRepository authorsRepository, BooksRepository booksRepository) {
        this.authorsRepository = authorsRepository;
        this.booksRepository = booksRepository;
        this.dbAuthors = authorsRepository.findAll();
        this.dbBooks = booksRepository.findAll();
    }

    public List<PersonModel> allAuthors() {
        dbAuthors = authorsRepository.findAll();
        return dbAuthors;
    }

    public List<Books> allBooks() {
        dbBooks = booksRepository.findAll();
        return dbBooks;
    }

    public void savePerson(BookData data) {
        var authorData = saveAuthor(data.authors().get(0));

        var newBookData = checkExistBook(data.title());
       if(newBookData == null) {
           authorData.setNewBook(new Books(data));
            authorsRepository.save(authorData);
           System.out.println("Se ha registrado un nuevo libro");
           newBookData = checkExistBook(data.title());
       }
     //   System.out.println(newBookData);
    }

    private PersonModel saveAuthor(PersonsData authorData) {
        var newAuthor = checkExistAuthor(authorData.name());
        if (newAuthor == null){
            authorsRepository.save(new PersonModel(authorData));
            System.out.println("Se ha registrado un nuevo autor");
            newAuthor = saveAuthor(authorData);
        }
        return newAuthor;
    }


    private  PersonModel checkExistAuthor(String name){
        dbAuthors = authorsRepository.findAll();
        var authorsData = dbAuthors.stream();
        var authorObject = authorsData.filter(author -> author.getName().equals(name)).toList();
        if(authorObject.isEmpty()){
            return null;
        }
        return authorObject.get(0);
    }

    private  Books checkExistBook(String name){
            dbBooks = booksRepository.findAll();
            var booksStream = dbBooks.stream();
            var authorObject = booksStream.filter(book -> book.getTitle().equals(name)).toList();
            if(authorObject.isEmpty()){
                return null;
            }
            return authorObject.get(0);
        }


    public void queryAuthorForYear(Integer query) {
        var authors = dbAuthors.stream().filter(
                personModel ->
                        (query < personModel.getDeathYear()) && (query > personModel.getBirthYear()) ).toList();
        if(authors.isEmpty()){
            PrinterEspecial.erroPrint("No se encontro ningun Author vivo en ese año");
            return;
        }
        authors.forEach(System.out::println);
    }

    public List<Books> searhListByLanguaje(String languajeQuery) {
        return dbBooks.stream().filter(books -> books.getLanguages().contains(languajeQuery)).toList();
    }
}
