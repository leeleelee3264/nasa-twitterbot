#! /bin/sh

echo ""
echo ""
echo "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
echo "Delete None docker image"
echo "Author: LeeLee"
echo "Date: 2023-01-18"
echo "This script is for deleting <None> docker images."
echo "Please run the script occasionally."
echo "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
echo ""
echo ""

IMAGES=$(docker images -f "dangling=true" -q)

if [ -z $IMAGES ]
then
    echo "There are no images to delete."
else
    docker rmi $IMAGES
fi