# spielerei-katalog-backend

docker-compose and spring expect the following environment variables:
```properties
MYSQL_DATABASE=a-database-name
MYSQL_ROOT_PASSWORD=a-database-pw
MYSQL_USER=a-user-name
MYSQL_PASSWORD=a-user-pw
```
preferably create a file called `.env` and export the variables to your shell by running 
```sh
export $(cat .env | xargs)
```
or
```sh
export $(xargs < .env)
```