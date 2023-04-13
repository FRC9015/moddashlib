This is a quick explainer/docs for how ModDash makes use of NetworkTables v4 for auto discovery and whatnot. The inline code blocks in headers indicate NT Table names.
### `/moddash`
| Entry Name | Type       | Describe                    |
| ---------- | ---------- | --------------------------- |
| `/md.tabs` | `String[]` | String array of all tab NAMES |
| Row 2      | Row 2      | Row 2                       |
| Row 3      | Row 3      | Row 3                       |

### `/moddash/{tab}`
| Entry Name        | Type       | Describe                         |
| ----------------- | ---------- | -------------------------------- |
| `/md.widgets`     | `String[]` | String array of all widget names |
| `/md.displayName` | `String`   | Tab display name                 |

### `/moddash/{tabID}/{widget}`
| Entry Name        | Type       | Describe                                                     |
| ----------------- | ---------- | ------------------------------------------------------------ |
| `/md.type`        | `String`   | The display type of the widget; number, boolean, angle, etc. |
| `/md.h_w`         | `int[]`    | Height and width for the widget, stored [height, width]      |
| `/md.x_y`         | `int[]`    | X & Y position of the widget, stored [x, y]                  |
| `/md.displayName` | `String`   | Display name for the widget                                  |
| ...               | any        | data for this widget; could be of any type                   |

