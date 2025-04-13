grammar Directives;

directive: BYTE_SIZE | TIME_DURATION;

BYTE_SIZE: DIGIT+ ('.' DIGIT+)? BYTE_UNIT;
TIME_DURATION: DIGIT+ ('.' DIGIT+)? TIME_UNIT;

fragment BYTE_UNIT: [kK][bB]? | [mM][bB]? | [gG][bB]? | [tT][bB]?;
fragment TIME_UNIT: [mM][sS] | [sS] | [mM][iI][nN] | [hH];
fragment DIGIT: [0-9];
