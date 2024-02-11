package ru.t1_academy.model;

import java.util.Objects;

public class SupportWords {
    private int id;
    private String words;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportWords that = (SupportWords) o;
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
