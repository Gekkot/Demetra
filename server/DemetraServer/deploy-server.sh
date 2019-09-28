#$1 - remote username
#$2 - deploy ip address


BUILD_FOLDER=./target
if [ -f "$BUILD_FOLDER" ]; then
    cd ./target
    scp DemetraServer-1.0-SNAPSHOT.jar $1@$2:~/demetra/
    scp -r ./lib $1@$2:~/demetra/
    echo "deploy!"
else 
    echo "chech build!"
fi