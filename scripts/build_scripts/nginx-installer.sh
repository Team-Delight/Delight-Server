read -p "route53 의 A address 를 nginx ip 로 변경하셨나요? [anykey/ctrl+c] : "

read -p "Enter your email address (ex : dohyung97022@gmail.com) : " EMAIL
read -p "Enter your website url (ex : api.adiy.info) : " URL

BACKEND_IP=''
BACKEND_IP_END='y'
UPSTREAM_CONFIGURATION=''

while [ $BACKEND_IP_END == 'y' ]
do
        read -p "Enter your backend ip (ex: 3.37.243.225) : " BACKEND_IP
        UPSTREAM_CONFIGURATION=$UPSTREAM_CONFIGURATION"server $BACKEND_IP:80 weight=100 max_fails=3 fail_timeout=3s; "
        read -p "Enter more backend ip? [y/n]  : " BACKEND_IP_END
        while [[ $BACKEND_IP_END != 'y' && $BACKEND_IP_END != 'n' ]]
        do
                read -p "Enter more backend ip? [y/n]  : " BACKEND_IP_END
        done
done

URL_CONFIGURATION="
upstream backend {
  $UPSTREAM_CONFIGURATION
}

server {
    server_name $URL;
    
    location / {
    include proxy_params;
    proxy_pass http://backend;
    proxy_redirect off;
    }
}
"

sudo apt-get update -y
sudo apt-get upgrade -y
sudo apt install nginx -y

rm /etc/nginx/sites-available/$URL
touch /etc/nginx/sites-available/$URL
echo $URL_CONFIGURATION > /etc/nginx/sites-available/$URL
sudo ln -s /etc/nginx/sites-available/$URL /etc/nginx/sites-enabled/$URL

sudo apt-get install -y software-properties-common
sudo add-apt-repository -y universe
sudo apt-get -y update
sudo apt-get install -y certbot python3-certbot-nginx

sudo certbot -n --nginx --agree-tos --redirect -d $URL -m $EMAIL

sudo systemctl start nginx