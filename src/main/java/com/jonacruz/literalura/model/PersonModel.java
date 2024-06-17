package com.jonacruz.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "person_data")
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Integer birthYear;
    private Integer deathYear;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Books> bookList;

    public PersonModel() {
    }

    public PersonModel(PersonsData personsData) {
        this.birthYear = personsData.birthYear();
        this.deathYear = personsData.deathYear();
        this.name = personsData.name();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

      public List<Books> getBookList() {
       return bookList;
     }

     private List<String> getStringsTitles() {
       return bookList.stream().map(Books::getTitle).toList();
     }


      public void setBookList(List<Books> bookList) {
          bookList.forEach(e -> e.setAuthor(this));
       this.bookList = bookList;
      }

      public void setNewBook(Books book){
        var listBook = this.bookList;
        listBook.add(book);
        setBookList(listBook);
      }

    @Override
    public String toString() {
        return String.format("""
                ======== Autor =========
                Author: %s
                Nacimiento: %s
                Fallecimiento: %s
                Libros : %s
                """,name,birthYear,deathYear,getStringsTitles());
    }
}