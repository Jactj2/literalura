package com.jonacruz.literalura.view;

import com.jonacruz.literalura.model.BookData;
import com.jonacruz.literalura.service.AuthorsService;
import com.jonacruz.literalura.repository.GutendexRepository;
import com.jonacruz.literalura.service.PrinterEspecial;
import com.jonacruz.literalura.service.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class ConsoleView {

    @Autowired
    private final AuthorsService service;

    @Autowired
    private final GutendexRepository repository;

    private final Scanner keyboard = new Scanner(System.in);

    public ConsoleView(AuthorsService service, GutendexRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    public Integer consoleMenu() {
        String option = keyboard.nextLine();
        if(Validators.validateInt(option)){
            return Integer.valueOf(option);
        }
        throw new NumberFormatException("La informacion ingresada no es un numero");
    }

    public void queryBook() throws Exception {

        var query = keyboard.nextLine();
        var dataQuery = repository.getQueryBook(query);
        if(dataQuery.isEmpty()) throw new IllegalStateException(Menssages.Error.NO_BOOKS);
        var book = menuBooksChoice(dataQuery);
        assert book != null;
        service.savePerson(book);
        System.out.println(book);
    }

    public void showAllBooks() {
     service.allBooks().forEach(System.out::println);
    }

    public void showAllAuthors() {
        service.allAuthors().forEach(System.out::println);
    }

    public void showAuthorForYear() {
        var query = keyboard.nextInt();
        keyboard.nextLine();
        service.queryAuthorForYear(query);
    }

    private BookData menuBooksChoice(List<BookData> bookDataList) throws Exception {
        AtomicInteger index = new AtomicInteger(1);
        AtomicInteger page = new AtomicInteger(1);
        var showMenuBooks = true;

        int totalPages = totalPages(bookDataList.size());
        int oldIndex = 1;
        do{
            System.out.printf((Menssages.INDEX_BOOK) + "%n",page,totalPages);
            var firstNumber = index.get() - 1;
            var lastNumber  = index.get() + 4;
            var bookLimitList = bookDataList.subList(firstNumber,lastNumber);
            bookLimitList.forEach(bookData ->{
                System.out.println(index + " - " + bookData.title() + " - " + bookData.authors());
                index.incrementAndGet();
            });
            System.out.println(Menssages.Navigation.NAVIGATION_COMPLETE);

            var keyboardPress = keyboard.nextLine();

            try {
                var choice = Integer.parseInt(keyboardPress);
                if(Validators.validateRange(choice,firstNumber+1,lastNumber)){
                    return bookDataList.get(choice - 1);
                }
                resetIndex(oldIndex,index);
            } catch (NumberFormatException exception){

                switch (keyboardPress.toLowerCase()){
                    case "x":
                        throw new Exception(Menssages.Error.EXITBOOK);
                    case "a":
                        resetIndex(oldIndex,index);
                        if( page.get() != 1 ){
                            page.decrementAndGet();
                            resetIndex(oldIndex,index);
                        }
                        break;
                    case "s":

                        if(page.get() != totalPages){
                            page.incrementAndGet();
                            break;
                        }
                        resetIndex(oldIndex,index);


                        break;
                    default:
                        PrinterEspecial.erroPrint(Menssages.Error.NO_OPTION);
                        resetIndex(oldIndex,index);
                }

            }

        } while (showMenuBooks);

        return null;
    }

    private int totalPages(int size) {
        if(size < 5) {
            return 1;
        }
        return size / 5;
    }

    private void resetIndex(int oldIndex, AtomicInteger index){
        oldIndex = index.get()-5;
        index.getAndSet(oldIndex);
    }


    public void showLanguajesBooksList() {
        var languajeQuery = keyboard.nextLine();
        if(languajeQuery.equals("es")  || languajeQuery.equals("en")  || languajeQuery.equals("fr") || languajeQuery.equals("pt")  ){
           var dataList =  service.searhListByLanguaje(languajeQuery);
           if(dataList.isEmpty()){
               throw new IllegalStateException(Menssages.Error.NO_BOOKS);
           }
           dataList.forEach(System.out::println);
            return;
        }
        throw new IllegalStateException(Menssages.Error.NO_OPTION);

    }
}
