package org.financetool.domain;

import java.util.ArrayList;
import java.util.List;

public class Statement {

    List<Operation> operationList = new ArrayList<>();

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void addOperation(Operation operation) {
        this.getOperationList().add(operation);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.getOperationList().stream().forEach(operation -> builder.append(operation + "\n"));
        return builder.toString();
    }
}
