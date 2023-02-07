#!/bin/bash
echo "(START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT)"
echo "----->EXECUTING git switch GVC"
git switch GVC
echo "----->EXECUTING git commit -am \"$1 - before merging GVC to main - ($(date))\""
git commit -am "$1 - before merging GVC to main - ($(date))"
echo "----->EXECUTING git push -u repoGVC GVC"
git push -u repoGVC GVC
echo "----->EXECUTING git push -u repoAC GVC"
git push -u repoAC GVC
echo "----->EXECUTING git switch main"
git switch main
echo "----->EXECUTING git pull"
git pull repoGVC main
echo "----->EXECUTING git merge GVC"
git merge GVC
echo "----->EXECUTING git push -u repoGVC main"
git push -u repoGVC main
echo "----->EXECUTING git push -u repoAC main"
git push -u repoAC main
echo "----->EXECUTING git switch GVC"
git switch GVC
echo "(END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT)"