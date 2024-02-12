package ru.t1academy.model;

import ru.t1academy.context.annotation.stereotype.Entity;

import java.util.Objects;

@Entity
public class SupportPhrase {
    private int id;
    private String words;

    public SupportPhrase(Integer id, String words) {
        this.id = id;
        this.words = words;
    }

    public int getId() {
        return id;
    }

    public String getWords() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportPhrase that = (SupportPhrase) o;
        return id == that.id && Objects.equals(words, that.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, words);
    }

    @Override
    public String toString() {
        return "SupportWords{" +
                "id=" + id +
                ", words='" + words + '\'' +
                '}';
    }
}
