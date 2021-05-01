# spielerei-katalog-backend

export the environment variables in .env by running 
```sh
export $(cat .env | xargs)
```
or
```sh
export $(xargs < .env)
```