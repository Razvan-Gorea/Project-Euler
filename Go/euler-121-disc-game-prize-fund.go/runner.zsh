#!/usr/bin/env zsh

# Set the TASK environment variable to the name of your Go file without the extension
TASK="euler-121-disc-game-prize-fund.go"

# Fail (exit) immediately if any of the following commands fail.
set -e

# The show-exec-command is available in the Einstein execution environment.
# It is also available in the `bin` directory in the project repo.  You can
# install it locally from there for testing.
# For Go, `show-exec-command go ${TASK}` will execute `go run ${TASK}.go`
for v in 0 3 5 8 10 12 15
do
  show-exec-command go run ${TASK} $v
done
