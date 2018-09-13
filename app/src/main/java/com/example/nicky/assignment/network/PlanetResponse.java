package com.example.nicky.assignment.network;

import java.util.List;

/**
 * Created by NICKY on 13-09-2018.
 */

public class PlanetResponse {

    private int count;
    private String next;
    private String previous;
    private List<Planets> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Planets> getResults() {
        return results;
    }

    public void setResults(List<Planets> results) {
        this.results = results;
    }
}
