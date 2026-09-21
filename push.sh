#!/usr/bin/env bash
# Usage: ./push.sh <github-username> [repo-name]
set -e
U=${1:?github username}; R=${2:-phi-safe-clinical-notes-assistant}
if command -v gh >/dev/null; then gh repo create "$U/$R" --public --source=. --remote=origin --push
else echo "Create an EMPTY repo named $R on github.com first, then press Enter"; read -r
  git remote add origin "https://github.com/$U/$R.git" 2>/dev/null || true; git branch -M main; git push -u origin main; fi