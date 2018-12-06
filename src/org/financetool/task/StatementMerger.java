package org.financetool.task;

import org.financetool.domain.Operation;
import org.financetool.domain.Statement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatementMerger {

    public Statement mergeStatements(List<Statement> statements) {
        Set<Operation> mergedOperations = new HashSet<>();
        for (final Statement statement : statements) {
            mergedOperations.addAll(statement.getOperationList());
        }

        return new Statement(new ArrayList<>(mergedOperations));
    }
}
