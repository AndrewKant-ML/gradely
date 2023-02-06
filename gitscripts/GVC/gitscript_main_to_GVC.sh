#!/bin/bash
echo "(START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT)"
echo "->EXECUTING git switch GVC"
git switch GVC
echo "->EXECUTING git commit -am \"$1 - before merging main to GVC - ($(date))\""
git commit -am "$1 - before merging main to GVC - ($(date))"
echo "->EXECUTING git push -u origin GVC"
git push -u origin GVC
echo "->EXECUTING git switch main"
git switch main
echo "->EXECUTING git pull"
git pull
echo "->EXECUTING git commit -am \"$1 - before merging main to GVC - ($(date))\""
git commit -am "$1 - before merging main to GVC - ($(date))"
echo "->EXECUTING git push -u origin main"
git push -u origin main
echo "->EXECUTING git switch GVC"
git switch GVC
echo "->EXECUTING git merge main"
git merge main
echo "->EXECUTING git push -u origin GVC"
git push -u origin GVC
echo "(END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT)"
