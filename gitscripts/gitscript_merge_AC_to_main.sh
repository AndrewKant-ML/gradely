#!/bin/bash
git switch AC
git commit -am "$1  (`date`)"
git push -u origin AC
git switch main
git pull
git merge AC
git push -u origin main
