#!/usr/bin/env zsh

# Set the TASK environment variable to the name of your Go file without the extension
TASK="euler-77-prime-summations.go"

# Fail (exit) immediately if any of the following commands fail.
set -e

# The show-exec-command is available in the Einstein execution environment.
# It is also available in the `bin` directory in the project repo.  You can
# install it locally from there for testing.
# For Go, `show-exec-command go ${TASK}` will execute `go run ${TASK}.go`
for v in 0 100 1000 10000 100000 1000000
do
  show-exec-command go run ${TASK} $v
done
