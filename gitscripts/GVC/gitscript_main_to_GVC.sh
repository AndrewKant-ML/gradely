#!/bin/bash
echo "(START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT)"
echo "----->EXECUTING git switch GVC"
git switch GVC
echo "----->EXECUTING git commit -am \"$1 - before merging main to GVC - ($(date))\""
git commit -am "$1 - before merging main to GVC - ($(date))"
echo "----->EXECUTING git push -u repoGVC GVC"
git push -u repoGVC GVC
echo "----->EXECUTING git push -u repoAC GVC"
git push -u repoAC GVC
echo "----->EXECUTING git switch main"
git switch main
echo "----->EXECUTING git pull"
git pull repoGVC main
echo "----->EXECUTING git commit -am \"$1 - before merging main to GVC - ($(date))\""
git commit -am "$1 - before merging main to GVC - ($(date))"
echo "----->EXECUTING git push -u repoGVC main"
git push -u repoGVC main
echo "----->EXECUTING git push -u repoAC main"
git push -u repoAC main
echo "----->EXECUTING git switch GVC"
git switch GVC
echo "----->EXECUTING git merge main"
git merge main
echo "----->EXECUTING git push -u repoGVC GVC"
git push -u repoGVC GVC
echo "----->EXECUTING git push -u repoAC GVC"
git push -u repoAC GVC
echo "(END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT)"
