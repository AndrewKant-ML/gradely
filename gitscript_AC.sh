#!/bin/bash
git switch AC
git commit -am "$1  (`date`)"
git push -u origin AC
