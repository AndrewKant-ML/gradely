#!/bin/bash
echo "(START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT)"
echo "----->EXECUTING git switch main"
git switch main
echo "----->EXECUTING git commit -am \"$1 - before merging main to AC - ($(date))\""
git commit -am "$1 - before merging main to AC - ($(date))"
echo "----->EXECUTING git push -u repoGVC main"
git push -u repoGVC main
echo "----->EXECUTING git push -u repoAC main"
git push -u repoAC main
echo "----->EXECUTING git switch AC"
git switch AC
echo "----->EXECUTING git pull"
git pull repoGVC AC
echo "----->EXECUTING git merge main"
git merge main
echo "----->EXECUTING git push -u repoGVC AC"
git push -u repoGVC AC
echo "----->EXECUTING git push -u repoAC AC"
git push -u repoAC AC
echo "----->EXECUTING git switch main"
git switch main
echo "(END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT)"
