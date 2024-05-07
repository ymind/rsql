package team.yi.rsql.core

import cz.jirutka.rsql.parser.ast.ComparisonNode

interface RsqlNodeValidator {
    fun validate(node: ComparisonNode): ComparisonNode?
}
