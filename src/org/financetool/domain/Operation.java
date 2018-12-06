package org.financetool.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Operation {

    private static final String CREDIT_SHORT_STRING = "CRE";
    private static final String DEBIT_SHORT_STRING = "DEB";

    private OperationType type;
    private Date date;
    private BigDecimal value;
    private String description;
    private Set<Tag> tags = new HashSet<>();

    public Operation() {
    }

    public Operation(OperationType type, Date date, BigDecimal value, String description) {
        this.type = type;
        this.date = date;
        this.value = value;
        this.description = description;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Operation) {
            Operation op = (Operation) o;
            if (this.type != null && op.type != null && !this.type.equals(op.type)) {
                return false;
            }
            if (this.date != null && op.date != null && !this.date.equals(op.date)) {
                return false;
            }
            if (this.value != null && op.value != null && !this.value.equals(op.value)) {
                return false;
            }
            if (this.description != null && op.description != null && !this.description.equals(op.description)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int dateHash = (this.date == null) ? 0 : this.date.hashCode();
        final int valueHash = (this.value == null) ? 0 : this.value.hashCode();
        final int descriptionHash = (this.description == null) ? 0 : this.description.hashCode();

        return dateHash + valueHash + descriptionHash;
    }
}
