🚀 New Feature: Support for Byte Size & Time Duration Units
As part of this enhancement, Wrangler now supports parsing and aggregating Byte Size and Time Duration units directly within recipes.

✅ Supported Units
Unit Type	Examples	Canonical Unit
Byte Size	10KB, 1.5MB, 2GB	Bytes
Time Duration	150ms, 2s, 1.5min	Milliseconds
🧠 How It Works
Two new token types have been added:

BYTE_SIZE → Parsed using ByteSize.java

TIME_DURATION → Parsed using TimeDuration.java

These can be used in directives as arguments and are automatically converted to bytes or milliseconds for computation.

🆕 New Directive: aggregate-stats
This directive performs aggregation over byte sizes and time durations.

🔧 Syntax:
text
Copy code
aggregate-stats :<size_column> :<time_column> <output_size_column> <output_time_column>
✅ Example:
text
Copy code
aggregate-stats :data_transfer :response_time total_size_mb total_time_sec
📊 Input Data (example rows):
data_transfer	response_time
1MB	1s
512KB	500ms
📤 Output:
total_size_mb	total_time_sec
1.5	1.5
🧪 Testing & AI Usage
Tests are written for both the parser classes and the directive.

AI tooling (ChatGPT) was used for:

Grammar writing

Directive scaffolding

Unit test generation

See prompts.txt in the repo for details.