package com.jonacruz.literalura.view;

public class Menssages {


    public static class Navigation {

        public static String NAVIGATION_COMPLETE = """
                \nA - Pagina Anterior
                S - Pagina Siguiente
                X - Salir
                """;
    }

    public static class Menu {
        public static String MAIN = """
            === Bienvenido a Literalura ===
            
            1 - Buscar Libro por titulo
            2 - Mostrar libros registrados
            3 - Mostrar autores registrados
            4 - Buscar autores vivos en determinado año
            5 - Buscar libros registrados por idioma
            
            0 - Salir
            
            ================================
            """;

        public static final String LANGUAJESEARCH = """
            ==== Busqueda por Lenguaje ====
            en - Ingles
            es - Español
            fr - Frances
            pt - Portugues
            """;
    }

    public static class Error {

        public static final String NUMBERS_STRING = "Elija un numero de los mostrados en la pantalla";

        public static final String NO_OPTION = "Ingrese una de las opciones mostradas en pantalla";

        public static final String NO_BOOKS = "No se encontro ningun libro" ;

        public static final String EXITBOOK = "Salio de la opcion buscar un libro por titulo";
    }

    public static String BOOKSEARCH = "Ingrese el nombre del libro que desea buscar";

    public static final String INDEX_BOOK = """
    =============================
    Pagina  %s de %s\s
    Elija una opcion de libro\s
    =============================
   \s""";

    public static final String YEARSEARCH = "Ingrese el año en el que deseea buscar al(los) autor(es) vivo(s)";




}
