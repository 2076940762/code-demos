#!/bin/bash
rm ".gitattributes" -rf 
git lfs track  "bigdata\hadoop\02-word-counting\src-data\word-count-data.txt"  
git lfs track  "bigdata\spark\01-hello-spark\bin\wordsconut\src-data\word-count-data.txt"  
git lfs track  "bigdata\spark\02-clustering\bin\wordsconut\src-data\word-count-data.txt"  
