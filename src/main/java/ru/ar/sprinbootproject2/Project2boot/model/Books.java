package ru.ar.sprinbootproject2.Project2boot.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "books")
public class Books {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;
        @Column(name = "title")
        private String title;
        @Column(name = "author")
        private String author;
        @Column(name = "year")
        private int year;


    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_created;


    @Transient
    private boolean result;


    public Books(String title, String author, int year) {
            this.title = title;
            this.author = author;
            this.year = year;
        }
        public Books(){

        }
        @ManyToOne()
        @JoinColumn(name = "id_people", referencedColumnName = "id")
        People owner;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }



    public Date getTime_created() {
        return time_created;
    }

    public void setTime_created(Date time_created) {
        this.time_created = time_created;
    }

    public People getOwner() {
        return owner;
    }

    public void setOwner(People owner) {
        this.owner = owner;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", owner=" + owner +
                '}';
    }

}
