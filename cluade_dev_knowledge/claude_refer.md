# Claude - Never lose your work
## Claude session: command to create claude session with custom name
claude -n "Bug-214"
claude resume ( which resume the session)

/rename -> rename the session

## claude branch vs fork

### branch vs fork in claude:
branch: create a branch from the current conversation
fork: fork will assign task to subagents with current conversation history so main session is free to work on
commands: /fork, /branch

## undo the changes by claude :: checkpoints in claude code::

checkpoints are safetynet. Claudecode automatically captures the state of your code before each edit, allowing you quickly undo changes
 and rewind to previous states if anything goes off track

### How to use checkpoints to rewind?
press esc twice or /rewind to open rewind menu. You then pick checkpoint and decide what to restore
- restore code only
- restore conversation only
- restore both
- summarize ( dont rewinf both)

  checkpoints dont track manual changes and file modified by bash commands. Also these live within session and expires in 30 days

use when - you need long term history, team collabaration use gitlab


 

