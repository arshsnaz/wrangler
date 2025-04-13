# Wrangler Enhancement: ByteSize and TimeDuration Support

This enhancement adds native support for parsing and handling Byte Size and Time Duration units in Wrangler transformation recipes.

## ✅ Features Added

- Support for byte sizes like `10KB`, `1.5MB`, `3GB`, etc.
- Support for time durations like `500ms`, `2s`, `1min`, etc.
- New aggregate directive: `aggregate-stats`

## 🔧 Usage Example

```wrangler
aggregate-stats :size :duration total_size_mb total_time_sec
```

### Example Input
| size | duration |
|------|----------|
| 10MB | 2s       |
| 20MB | 3s       |

### Output
| total_size_mb | total_time_sec |
|----------------|----------------|
| 30.0           | 5.0            |

## 📁 Modules Modified
- wrangler-api
- wrangler-core

## 🧪 Tests
- Unit tests for ByteSize and TimeDuration
- Integration test for aggregate-stats directive

## 🤖 AI Tool Prompts Used
Check `prompts.txt` (if applicable)
