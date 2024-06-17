package com.jonacruz.literalura.main;

import com.jonacruz.literalura.service.PrinterEspecial;
import com.jonacruz.literalura.view.ConsoleView;
import com.jonacruz.literalura.view.Menssages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AppMain {


    public AppMain(ConsoleView console) {
        this.console = console;
    }

    @Autowired
    private final ConsoleView console;


    public void showMenu() {
        var continueLoop = true;
        do{
            System.out.println(Menssages.Menu.MAIN);

            try {
               continueLoop = showChoice();
            } catch (Exception e){
                PrinterEspecial.erroPrint(e.getMessage());
            }


            System.out.println('\n');
        } while(continueLoop);
    }

    public boolean showChoice() throws Exception {

        var choice = console.consoleMenu();
        switch (choice) {
            case 1:
                System.out.println(Menssages.BOOKSEARCH);
                console.queryBook();
                break;
            case 2:
                console.showAllBooks();
                break;
            case 3:
                console.showAllAuthors();
                break;
            case 4:
                System.out.println(Menssages.YEARSEARCH);
                console.showAuthorForYear();
                break;
                case 5:
                System.out.println(Menssages.Menu.LANGUAJESEARCH);
                console.showLanguajesBooksList();
                break;
            case 0:
                return false;
            default:
                throw new Exception(Menssages.Error.NO_OPTION);

        }
        return true;
    }


}
