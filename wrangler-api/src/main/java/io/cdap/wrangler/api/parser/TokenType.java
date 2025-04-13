package io.cdap.wrangler.api.parser;

/**
 * This enum defines the various types of tokens supported by the Wrangler
 * parser.
 */
public enum TokenType {
    // Existing token types
    COLUMN_NAME,
    STRING,
    INTEGER,
    BOOLEAN,
    DOUBLE,
    DATE,
    IDENTIFIER,
    PATTERN,
    REGEX,
    EXPRESSION,
    // ✅ New token types added for the Zeotap assignment
    BYTE_SIZE, // E.g., "10KB", "1.5MB", "2GB"
    TIME_DURATION    // E.g., "150ms", "2s", "1.5min"
}
