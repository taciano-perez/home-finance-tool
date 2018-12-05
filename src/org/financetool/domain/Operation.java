package org.financetool.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Operation {

    private static final String CREDIT_SHORT_STRING = "CRE";
    private static final String DEBIT_SHORT_STRING = "DEB";

    OperationType type;
    Date date;
    BigDecimal value;
    String description;
    Set<Tag> tags = new HashSet<>();

    public OperationType getType() {
        return type;
    }

    public String getTypeAsShortString() {
        if (this.getType() == null) {
            return null;
        }
        if (this.getType().equals(OperationType.CREDIT)) {
            return CREDIT_SHORT_STRING;
        } else {
            return DEBIT_SHORT_STRING;
        }
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "Type=" + this.getTypeAsShortString() + " Date=" + this.getDate() + " Value=" + this.getValue() + " Description=" + this.getDescription();
    }
}
