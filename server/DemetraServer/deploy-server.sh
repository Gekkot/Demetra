#$1 - remote username
#$2 - deploy ip address
cd ./target || exit1
scp DemetraServer-1.0-SNAPSHOT.jar $1@$2:~/demetra/
scp -r ./lib $1@$2:~/demetra/
echo "deploy!"
