#!/bin/bash
git switch main
git commit -am "$1  (`date`)"
git push -u origin main
git switch AC
git pull
git merge
git push -u origin AC
