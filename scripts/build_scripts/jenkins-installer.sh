# 업데이트
sudo apt-get update
sudo apt-get -y upgrade

# jdk 설치
sudo apt -y install openjdk-8-jdk

# jenkins 설치
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
echo "deb https://pkg.jenkins.io/debian-stable binary/" >> /etc/apt/sources.list
sudo apt update
sudo apt install jenkins -y

# jenkins 시작
sudo systemctl start jenkins
sudo systemctl enable jenkins
sudo systemctl status jenkins

# jenkins 비밀번호 표시
echo the password for jenkins is :
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

# 아이피, 포트번호 표시
curl http://checkip.amazonaws.com
echo port :8080

# sh 스크립트가 다 돌아갔다고 설치가 끝난게 아닙니다.
# jenkins 에 들어가서 추가 설정을 하셔야 합니다.

# 자세한 내용은 https://github.com/dohyung97022/simpleJenkinsGuide 참고