package io.cdap.wrangler.parse;

import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.parser.Token;
import io.cdap.wrangler.parser.DirectivesBaseVisitor;
import io.cdap.wrangler.parser.DirectivesParser;

public class RecipeVisitor extends DirectivesBaseVisitor<Token> {

    @Override
    public Token visitByteSizeArg(DirectivesParser.ByteSizeArgContext ctx) {
        return new ByteSize(ctx.getText());
    }

    @Override
    public Token visitTimeDurationArg(DirectivesParser.TimeDurationArgContext ctx) {
        return new TimeDuration(ctx.getText());
    }

    // You would also have other visit methods here for STRING, INTEGER, COLUMN_NAME, etc.
    // This file assumes that the ANTLR grammar and base visitor have already been generated using mvn compile.
}
