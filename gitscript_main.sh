#!/bin/bash
git switch main
git commit -am "$1  (`date`)"
git push -u origin main
