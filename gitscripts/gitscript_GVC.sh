#!/bin/bash
git switch GVC
git commit -am "$1  (`date`)"
git push -u origin GVC
