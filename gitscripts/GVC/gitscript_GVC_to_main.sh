#!/bin/bash
echo "(START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT)"
echo "----->EXECUTING git switch GVC"
git switch GVC
echo "----->EXECUTING git commit -am \"$1 - before merging GVC to main - ($(date))\""
git commit -am "$1 - before merging GVC to main - ($(date))"
echo "----->EXECUTING git push -u origin GVC"
git push -u origin GVC
echo "----->EXECUTING git push -u copy GVC"
git push -u copy GVC
echo "----->EXECUTING git switch main"
git switch main
echo "----->EXECUTING git pull"
git pull origin main
echo "----->EXECUTING git merge GVC"
git merge GVC
echo "----->EXECUTING git push -u origin main"
git push -u origin main
echo "----->EXECUTING git push -u copy main"
git push -u copy main
echo "----->EXECUTING git switch GVC"
git switch GVC
echo "(END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT)"