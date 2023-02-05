#!/bin/bash
echo "(START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT) (START OF SCRIPT)"
echo "->EXECUTING git switch AC"
git switch AC
echo "->EXECUTING git commit -am \"$1  ($(date))\""
git commit -am "$1  ($(date))"
echo "->EXECUTING git push -u origin AC"
git push -u origin AC
echo "->EXECUTING git push -u copy AC"
git push -u copy AC
echo "->EXECUTING git switch main"
git switch main
echo "->EXECUTING git commit -am \"$1  ($(date))\""
git commit -am "$1  ($(date))"
echo "->EXECUTING git push -u origin main"
git push -u origin main
echo "->EXECUTING git push -u copy main"
git push -u copy main
echo "->EXECUTING git switch AC"
git switch AC
echo "->EXECUTING git pull"
git pull
echo "->EXECUTING git merge main"
git merge main
echo "->EXECUTING git push -u origin AC"
git push -u origin AC
echo "->EXECUTING git push -u copy AC"
git push -u copy AC
echo "(END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT) (END OF SCRIPT)"