package org.financetool.domain;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    String name;
    List<String> descriptionPatterns = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescriptionPatterns() {
        return descriptionPatterns;
    }

    public void addDescriptionPattern(String descriptionPattern) {
        this.descriptionPatterns.add(descriptionPattern);
    }
}
