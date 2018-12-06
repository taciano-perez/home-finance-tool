package org.financetool.domain;

import java.util.ArrayList;
import java.util.List;

public class Statement {

    private List<Operation> operationList = new ArrayList<>();

    public Statement() {
    }

    public Statement(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void addOperation(Operation operation) {
        this.getOperationList().add(operation);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.getOperationList().forEach(operation -> builder.append(operation).append("\n"));
        return builder.toString();
    }
}
