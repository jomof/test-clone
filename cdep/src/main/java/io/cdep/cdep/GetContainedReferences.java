package io.cdep.cdep;

import io.cdep.annotations.NotNull;
import io.cdep.cdep.ast.finder.AssignmentBlockExpression;
import io.cdep.cdep.ast.finder.AssignmentExpression;
import io.cdep.cdep.ast.finder.AssignmentReferenceExpression;
import io.cdep.cdep.ast.finder.Expression;

import java.util.ArrayList;
import java.util.List;

public class GetContainedReferences extends ReadonlyVisitor {
    final public List<AssignmentExpression> list = new ArrayList<>();

    public GetContainedReferences(Expression expression) {
        visit(expression);
    }

    @Override
    public void visitAssignmentReferenceExpression(@org.jetbrains.annotations.NotNull @NotNull AssignmentReferenceExpression expr) {
        super.visit(expr.assignment);
        list.add(expr.assignment);
    }

    @Override
    protected void visitAssignmentBlockExpression(@org.jetbrains.annotations.NotNull @NotNull AssignmentBlockExpression expr) {
        // Don't count assign block
        visit(expr.statement);
    }
}
