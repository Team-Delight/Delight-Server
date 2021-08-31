CURRENT_PID=$(pgrep -f "java -jar")
if [ -z "$CURRENT_PID" ]; then
     echo "현재 구동중인 애플리케이션이 없음으로 종료하지 않습니다."
else
     echo "현재 구동중인 애플리케이션을 종료합니다."
     sudo kill -9 $CURRENT_PID
     sudo sleep 5
fi

sudo rm delightserver-0.0.1-SNAPSHOT.jar
sudo cp libs/delightserver-0.0.1-SNAPSHOT.jar delightserver-0.0.1-SNAPSHOT.jar
sudo nohup java -jar -Dspring.profiles.active=prod *.jar> delightserver.out 2>&1 &

BUILD_SUCCESSFUL=0
for i in $(seq 1 50)
do
   sudo sleep 2
   echo "서버를 $i번 확인중입니다."

   RESP=$(sudo curl -s localhost/api/hello)

   if [ $RESP = "Hello" ]
   then
     echo "서버가 시작되었습니다."
     BUILD_SUCCESSFUL=1
     break
   else
     echo "서버가 준비중입니다."
   fi
done

if [ $BUILD_SUCCESSFUL = 0 ]
then
   echo "배포가 성공하지 않았습니다."
   exit 1
fi
exit 0